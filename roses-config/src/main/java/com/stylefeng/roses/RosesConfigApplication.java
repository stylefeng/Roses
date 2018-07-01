package com.stylefeng.roses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 配置中心启动类
 *
 * <pre>
 * 获取git上的配置信息的访问地址:
 *      /{application}/{profile}/{label}
 *      /{application}-{profile}.yml
 *      /{label}/{application}-{profile}.yml
 *      /{application}-{profile}.properties
 *      /{label}/{application}-{profile}.properties
 * </pre>
 *
 * @author stylefeng
 * @Date 2018/1/17 23:00
 */
@SpringBootApplication
@EnableConfigServer
public class RosesConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesConfigApplication.class, args);
    }

    @Configuration
    public class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable();
        }
    }
}
