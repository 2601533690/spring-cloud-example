package com.example.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAdminApplication.class, args);
    }

}
