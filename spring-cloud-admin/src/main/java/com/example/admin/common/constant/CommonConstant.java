package com.example.admin.common.constant;

import org.springframework.core.Ordered;

public class CommonConstant {

    public static final String AID_ATTR_NAME = "ACCOUNT_ID";

    public static final String MDC_AID_KEY = "accountId";

    public static final String TOKEN_HEADER_NAME = "token";

    public static final String CLAIM_AID_KEY = "aid";

    public static final String METHOD_TRACE_ENTER_MSG = "<ENTER> $[targetClassName]#$[methodName]...";

    public static final String METHOD_TRACE_EXIT_MSG
            = "<EXIT> $[targetClassName]#$[methodName], took $[invocationTime]ms.";

    public static final String METHOD_TRACE_EXCEPTION_MSG = "<EXCEPTION> $[targetClassName]#$[methodName].";

    public static final Integer METHOD_TRACE_INTERCEPT_ORDER = Ordered.LOWEST_PRECEDENCE - 9;
}
