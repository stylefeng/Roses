package com.stylefeng.roses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class RosesDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesDashboardApplication.class, args);
    }
}
