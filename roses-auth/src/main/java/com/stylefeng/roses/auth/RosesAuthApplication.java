package com.stylefeng.roses.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class RosesAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesAuthApplication.class, args);
    }
}
