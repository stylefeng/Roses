package com.stylefeng.roses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RosesRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesRegisterApplication.class, args);
    }
}
