package com.stylefeng.roses.gate;

import com.stylefeng.roses.core.context.AuthServiceConsumer;
import com.stylefeng.roses.gate.consumer.MessageServiceConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关服务
 *
 * @author fengshuonan
 * @Date 2017/11/10 上午11:24
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(clients = {AuthServiceConsumer.class, MessageServiceConsumer.class})
@EnableZuulProxy
public class RosesGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesGatewayApplication.class, args);
    }
}
