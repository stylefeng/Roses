package com.stylefeng.roses.core.config;

import com.stylefeng.roses.core.config.properties.AppNameProperties;
import com.stylefeng.roses.core.config.properties.LogProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 默认的配置
 *
 * @author fengshuonan
 * @date 2018-01-07 12:33
 */
@Configuration
@PropertySource("classpath:/default-config.properties")
public class DefaultProperties {

    @Bean
    @ConfigurationProperties(prefix = "roses.log")
    public LogProperties logProperties() {
        return new LogProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.application.name")
    public AppNameProperties appNameProperties() {
        return new AppNameProperties();
    }
}
