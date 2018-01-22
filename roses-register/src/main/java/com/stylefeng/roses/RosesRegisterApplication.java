package com.stylefeng.roses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心服务
 *
 * @author stylefeng
 * @Date 2018/1/22 21:26
 */
@SpringBootApplication
@EnableEurekaServer
public class RosesRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesRegisterApplication.class, args);
    }
}
