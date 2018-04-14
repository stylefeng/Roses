package com.stylefeng.roses.auth.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * auth的配置
 *
 * @author fengshuonan
 * @date 2018-04-13 23:31
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("stylefeng", "123456");
    }
}
