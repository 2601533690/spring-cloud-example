package com.example.admin.common.config;

import com.example.admin.common.constant.CommonConstant;
import com.example.admin.common.operation.MethodTraceInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class OperationConfig {

    @Value("${example.opr.method-trace-enabled}")
    private String methodTraceEnabled;

    @Value("${example.opr.method-trace-pointcut}")
    private String methodTracePointcut;

    @Value("${example.opr.method-trace-allow-enter}")
    private String allowEnter;

    @Value("${example.opr.method-trace-allow-exit}")
    private String allowExit;

    @Value("${example.opr.method-trace-allow-exception}")
    private String allowException;

    @Bean
    public MethodTraceInterceptor methodTraceInterceptor() {
        Boolean traceEnabled = Boolean.valueOf(methodTraceEnabled);
        MethodTraceInterceptor interceptor = new MethodTraceInterceptor(
                traceEnabled,
                allowEnter,
                allowExit,
                allowException);
        interceptor.setEnterMessage(CommonConstant.METHOD_TRACE_ENTER_MSG);
        interceptor.setExitMessage(CommonConstant.METHOD_TRACE_EXIT_MSG);
        interceptor.setExceptionMessage(CommonConstant.METHOD_TRACE_EXCEPTION_MSG);

        return interceptor;
    }

    @Bean
    @ConditionalOnProperty(
            name = "example.opr.method-trace-enabled",
            havingValue = "true")
    public Advisor methodTraceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(methodTracePointcut);

        DefaultPointcutAdvisor advisor =
                new DefaultPointcutAdvisor(pointcut, methodTraceInterceptor());
        advisor.setOrder(CommonConstant.METHOD_TRACE_INTERCEPT_ORDER);

        return advisor;
    }
}
