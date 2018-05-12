package com.stylefeng.roses.gate.config;

import com.stylefeng.roses.core.config.properties.SignProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件获取
 *
 * @author fengshuonan
 * @date 2018-05-08-下午3:57
 */
@Configuration
public class PropertiesConfig {

    @Bean
    @ConfigurationProperties(prefix = "roses.sign")
    public SignProperties signProperties() {
        return new SignProperties();
    }

}
