package com.example.consumer.controller;

import com.example.consumer.feign.ConsumerFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ConsumerController {

    @Autowired
    private ConsumerFeign consumerFeign;

    @GetMapping(value = "/index")
    public String index() {
        return "Hello Consumer!";
    }


    @GetMapping(value = "/getInfo")
    public String getProvider() {
        return "消费者调用生产者：" + consumerFeign.index();
    }
}
