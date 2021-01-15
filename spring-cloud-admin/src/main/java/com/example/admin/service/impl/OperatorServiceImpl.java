package com.example.admin.service.impl;

import com.example.admin.common.util.DateUtil;
import com.example.admin.dao.AdminDao;
import com.example.admin.dao.MenuDao;
import com.example.admin.dto.operator.LoginDto;
import com.example.admin.dto.operator.UserMenuListDto;
import com.example.admin.po.AdminPo;
import com.example.admin.po.MenuPo;
import com.example.admin.service.OperatorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private MenuDao menuDao;

    @Value("${auth.session.expiration}")
    private Long expiration;

    @Override
    public LoginDto login(String username, String password) {
        // token
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.sha1Hex(password));
        // security subject
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();

        AdminPo adminPo = adminDao.selectByUsername(username);
        adminPo.setLastTime(DateUtil.currentTimeInLong());
        adminDao.updateById(adminPo);
        // current session
        Session currentsSession = SecurityUtils.getSubject().getSession();
        //设置session过期时间毫秒，这里是1天
        currentsSession.setTimeout(expiration);
        // return vo
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(((AdminPo) SecurityUtils.getSubject().getPrincipal()).getUsername());
        loginDto.setToken((String) currentsSession.getId());
        return loginDto;
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (SessionException e) {
            // no action here.
        }
    }


    @Override
    public List<MenuPo> getMenuByUserId(Long userId) {
        return menuDao.selectListByUserId(userId);
    }

    public AdminPo getUserByUsername(String username) {
        return adminDao.selectByUsername(username);
    }

    private List<UserMenuListDto> getChild(Long id, List<UserMenuListDto> userMenuListDtoList) {
        List<UserMenuListDto> childMenuList = new ArrayList<>();
        userMenuListDtoList.forEach(listMenuListItemVo -> {
            if (listMenuListItemVo.getParentId().equals(id)) {
                childMenuList.add(listMenuListItemVo);
            }
        });

        childMenuList.forEach(listMenuListItemVo -> {
            listMenuListItemVo.setChildMenuList(getChild(listMenuListItemVo.getId(), userMenuListDtoList));
        });

        if (childMenuList.size() == 0) {
            return new ArrayList<>();
        }
        return childMenuList;
    }
}
