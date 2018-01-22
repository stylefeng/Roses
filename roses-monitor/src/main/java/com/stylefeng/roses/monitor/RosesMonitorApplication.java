package com.stylefeng.roses.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 监控服务
 *
 * @author stylefeng
 * @Date 2018/1/22 21:26
 */
@SpringBootApplication
@EnableAdminServer
public class RosesMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesMonitorApplication.class, args);
    }
}
