package com.example.admin.common.R;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RAspect {

    private final String R_POINT_CUT = "execution(@org.springframework.web.bind.annotation.ResponseBody * *(..))";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private HttpServletRequest request;

    @AfterReturning(value = R_POINT_CUT, returning = "res")
    public void doAfterController(Object res) {
        if (res instanceof R) {
            R r = (R) res;
            String message = messageSource.getMessage(r.getI18nMessage(),
                    r.getMsgParams(),
                    request.getLocale());
            r.setMessage(message);
        }
    }
}
