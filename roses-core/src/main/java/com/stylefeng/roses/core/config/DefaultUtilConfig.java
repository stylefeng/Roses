package com.stylefeng.roses.core.config;

import com.stylefeng.roses.core.util.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认的工具类
 *
 * @author fengshuonan
 * @date 2018-01-07 12:33
 */
@Configuration
public class DefaultUtilConfig {

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
