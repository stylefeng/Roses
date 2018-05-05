package com.stylefeng.roses.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 消息恢复子系统
 *
 * @author stylefeng
 * @Date 2018/1/22 21:27
 */
@SpringBootApplication
@EnableFeignClients("com.stylefeng.roses.order.modular.consumer")
@EnableDiscoveryClient
public class RosesOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesOrderApplication.class, args);
    }
}
