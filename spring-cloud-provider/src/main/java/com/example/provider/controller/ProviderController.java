package com.example.provider.controller;

import org.springframework.web.bind.annotation.*;


@RestController
public class ProviderController {

    @GetMapping(value = "/index")
    public String index() {
        return "Hello Provider!";
    }

}
