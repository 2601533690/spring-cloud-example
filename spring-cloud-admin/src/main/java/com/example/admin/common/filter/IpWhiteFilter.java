package com.example.admin.common.filter;

import com.example.admin.common.R.R;
import com.example.admin.common.R.RStatus;
import com.example.admin.common.util.ServletRequestUtil;
import com.example.admin.component.filter.FilterMessageBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class IpWhiteFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private AdminIpDao adminIpDao;

    @Autowired
    private FilterMessageBuilder filterMessageBuilder;

    @Value("${admin.ip-whitelist.enabled}")
    Boolean ipWhitelistEnabled;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (ipWhitelistEnabled) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            String clientIp = ServletRequestUtil.getClientIp(httpRequest);
//            AdminIpPo adminIpPo = adminIpDao.selectAdminIpByIp(clientIp);
//            if (adminIpPo == null) {
//                servletResponse.reset();
//                httpResponse.setHeader("Content-type","application/json;charset=utf-8");
//                httpResponse.setHeader("Access-Control-Allow-Origin", "*");
//                httpResponse.setHeader("Access-Control-Allow-Methods", "*");
//                httpResponse.setHeader("Access-Control-Allow-Headers", "*, token");
//                servletResponse.getWriter().write(new ObjectMapper().writeValueAsString(
//                        filterMessageBuilder.buildMessage(servletRequest, R.status(RStatus.IP_NOT_IN_WHITELIST))));
//                return;
//            }

        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
