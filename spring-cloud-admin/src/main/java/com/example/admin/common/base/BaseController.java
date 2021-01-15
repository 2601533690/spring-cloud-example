package com.example.admin.common.base;

import com.example.admin.common.R.R;
import com.example.admin.common.R.RException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {

    @ExceptionHandler({
            RException.class
    })

    @ResponseBody
    public R handleException(RException ex) {
        return R.status(ex.getStatus(), ex.getMsgParams()).data(ex.getData());
    }
}
