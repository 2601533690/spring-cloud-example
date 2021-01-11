package com.example.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider-server", fallbackFactory = FeignServiceFallBack.class)
public interface ConsumerFeign {

    @GetMapping("/index")
    String index();
}
