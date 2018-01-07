package com.stylefeng.roses.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.stylefeng.roses")
public class RosesAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesAuthApplication.class, args);
    }
}
