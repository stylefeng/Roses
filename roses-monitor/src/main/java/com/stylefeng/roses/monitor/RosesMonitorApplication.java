package com.stylefeng.roses.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class RosesMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesMonitorApplication.class, args);
    }
}
