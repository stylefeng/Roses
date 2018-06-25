package com.stylefeng.roses.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 让roses-monitor监控调用到，内网环境不用开保护
 *
 * @author stylefeng
 * @Date 2018/6/24 23:13
 */
@Configuration
@ConditionalOnClass(WebSecurityConfigurerAdapter.class)
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll()
                .and().csrf().disable();
    }
}