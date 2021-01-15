package com.example.admin.component.spring;

import com.example.admin.common.R.R;
import com.example.admin.common.R.RStatus;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GlobalErrorController implements ErrorController {

    private static final String ERROR_MAPPING = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_MAPPING;
    }

    @RequestMapping(value = ERROR_MAPPING)
    @ResponseBody
    public R error() {
        return R.status(RStatus.SYSTEM_ERROR);
    }
}
