package com.stylefeng.roses.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * 监控中心启动类
 *
 * @author stylefeng
 * @Date 2018/6/24 22:28
 */
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAdminServer
public class RosesMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesMonitorApplication.class, args);
    }

}

