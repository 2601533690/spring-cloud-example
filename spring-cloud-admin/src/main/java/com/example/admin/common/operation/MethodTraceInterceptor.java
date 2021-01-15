package com.example.admin.common.operation;

import com.example.admin.common.R.RException;
import com.example.admin.common.constant.CommonConstant;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.slf4j.MDC;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class MethodTraceInterceptor extends CustomizableTraceInterceptor {

    private static final long serialVersionUID = 1L;

    private final Boolean methodTraceEnabled;
    private final String allowEnter;
    private final String allowExit;
    private final String allowException;


    public MethodTraceInterceptor(Boolean methodTraceEnabled, String allowEnter, String allowExit,
                                  String allowException) {
        super();
        this.methodTraceEnabled = methodTraceEnabled;
        this.allowEnter = allowEnter;
        this.allowExit = allowExit;
        this.allowException = allowException;
    }

    @Override
    protected Object invokeUnderTrace(MethodInvocation invocation, Log logger) throws Throwable {
        try {
            ServletRequestAttributes attr =
                    (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = attr.getRequest();
            String accountId = (String) request.getAttribute(CommonConstant.AID_ATTR_NAME);
            if (!accountId.equals(MDC.get(CommonConstant.MDC_AID_KEY))) {
                MDC.remove(CommonConstant.MDC_AID_KEY);
                MDC.put(CommonConstant.MDC_AID_KEY, accountId);
            }
        } catch (Exception e) {
            // intentionally empty
        }

        try {
            return super.invokeUnderTrace(invocation, logger);
        } finally {
            MDC.remove(CommonConstant.MDC_AID_KEY);
        }
    }

    @Override
    protected void writeToLog(Log logger, String message, Throwable ex) {
        if(ex instanceof RException) {
            return;
        }
        if (shouldLog(message)) {
            if (ex != null) {
                logger.info(message, ex);
            } else {
                logger.info(message);
            }
        }
    }

    private Boolean shouldLog(String msg) {
        if ("false".equals(allowEnter) && StringUtils.startsWithIgnoreCase(msg, "<ENTER>")) {
            return false;
        }

        if ("false".equals(allowExit) && StringUtils.startsWithIgnoreCase(msg, "<EXIT>")) {
            return false;
        }

        if ("false".equals(allowException) && StringUtils.startsWithIgnoreCase(msg, "<EXCEPTION>")) {
            return false;
        }

        return true;
    }

    @Override
    protected boolean isLogEnabled(Log logger) {
        return methodTraceEnabled;
    }

}
