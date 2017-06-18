package com.stylefeng.roses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Roses启动类
 *
 * @author stylefeng
 * @Date 2017年5月29日21:30:19
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RosesApplication {

    protected final static Logger logger = LoggerFactory.getLogger(RosesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RosesApplication.class, args);
        logger.info("RosesApplication is sussess!");
    }
}
