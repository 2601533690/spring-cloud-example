package com.example.admin.controller;

import com.example.admin.common.R.R;
import com.example.admin.common.R.RStatus;
import com.example.admin.common.operation.annotation.NeedMethodTrace;
import com.example.admin.dto.operator.LoginDto;
import com.example.admin.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/operator")
@Validated
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    //登录
    @NeedMethodTrace
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public R login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        LoginDto result = operatorService.login(username, password);
        return R.status(RStatus.OK).data(result);
    }

    //登出
    @NeedMethodTrace
    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    @ResponseBody
    public R logout() {
        operatorService.logout();
        return R.status(RStatus.OK);
    }

}
