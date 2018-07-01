package com.stylefeng.roses.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 默认actuator安全配置
 *
 * @author stylefeng
 * @Date 2018/6/26 21:31
 */
@Configuration
@ConditionalOnClass(WebSecurityConfigurerAdapter.class)
public class DefaultActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll()
                .and().csrf().disable();
    }
}