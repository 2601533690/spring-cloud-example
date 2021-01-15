package com.example.admin.component.filter;

import com.example.admin.common.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;

@Component
public class FilterMessageBuilder {

    @Autowired
    private MessageSource messageSource;

    // no aspect impl
    public Object buildMessage(ServletRequest request, Object res) {
        if (res instanceof R) {
            R r = (R) res;
            String message = messageSource.getMessage("r.status." + r.getCode(),
                    r.getMsgParams(),
                    request.getLocale());
            r.setMessage(message);
        }
        return res;
    }
}
