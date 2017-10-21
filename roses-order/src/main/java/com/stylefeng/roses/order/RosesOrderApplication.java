package com.stylefeng.roses.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.stylefeng.roses")
@EnableFeignClients
@EnableDiscoveryClient
public class RosesOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesOrderApplication.class, args);
    }
}

