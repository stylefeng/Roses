package com.stylefeng.roses.message.timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 消息服务
 *
 * @author stylefeng
 * @Date 2018/1/22 21:27
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class RosesMessageTimerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesMessageTimerApplication.class, args);
    }
}
