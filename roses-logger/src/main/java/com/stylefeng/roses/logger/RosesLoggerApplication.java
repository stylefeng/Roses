package com.stylefeng.roses.logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 日志服务，监听队列中的日志，并存储到数据库
 *
 * @author stylefeng
 * @Date 2018/1/22 21:27
 */
@SpringBootApplication
@EnableScheduling
public class RosesLoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesLoggerApplication.class, args);
    }

}
