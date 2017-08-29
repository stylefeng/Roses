package com.stylefeng.roses.config;

import com.stylefeng.roses.filter.HystrixRequestContextFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * web配置
 *
 * @author fengshuonan
 * @date 2017年8月29日11:52:49
 */
@Configuration
public class WebConfig {

    @Bean
    public HystrixRequestContextFilter jwtAuthenticationTokenFilter() {
        return new HystrixRequestContextFilter();
    }
}
