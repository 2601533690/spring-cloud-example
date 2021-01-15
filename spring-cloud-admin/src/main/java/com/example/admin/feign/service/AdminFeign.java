package com.example.admin.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider-server", fallbackFactory = FeignServiceFallBack.class)
public interface AdminFeign {

    @GetMapping("/index")
    String index();
}
