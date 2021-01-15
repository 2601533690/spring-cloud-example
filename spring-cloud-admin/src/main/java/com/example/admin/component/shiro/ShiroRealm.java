package com.example.admin.component.shiro;

import com.example.admin.po.AdminPo;
import com.example.admin.po.MenuPo;
import com.example.admin.service.OperatorService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShiroRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OperatorService operatorService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // get operator user
        AdminPo adminPo = (AdminPo) principalCollection.getPrimaryPrincipal();

        // authorization info
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // shiro service get resources by operator id
        List<MenuPo> menus = operatorService.getMenuByUserId(adminPo.getId());

        // add permissions
        if (menus != null) {
            for (MenuPo menu : menus) {
                authorizationInfo.addStringPermission(menu.getUri());
            }
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        AdminPo adminPo = operatorService.getUserByUsername(username);

        if (adminPo == null) {
            throw new UnknownAccountException();
        }

        if (adminPo.getDisabled()) {
            throw new LockedAccountException();
        }

        // not salt here
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                adminPo,
                adminPo.getPassword(),
                adminPo.getUsername()
        );

        return authenticationInfo;
    }
}
