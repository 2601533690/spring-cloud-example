package com.example.admin.feign.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignServiceFallBack implements FallbackFactory<AdminFeign> {

    @Override
    public AdminFeign create(Throwable throwable) {
        return new AdminFeign() {
            @Override
            public String index() {
                return "管理端后台调用！！！";
            }
        };
    }
}
