package com.stylefeng.roses.gate;

import com.stylefeng.roses.core.context.AuthServiceConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 网关服务
 *
 * @author fengshuonan
 * @Date 2017/11/10 上午11:24
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(clients = AuthServiceConsumer.class)
@EnableZuulProxy
public class RosesGatewayApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(RosesGatewayApplication.class, args);
    }
}
