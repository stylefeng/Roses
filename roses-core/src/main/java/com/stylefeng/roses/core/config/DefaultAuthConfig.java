package com.stylefeng.roses.core.config;

import com.stylefeng.roses.core.context.UserContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认auth远程调用配置
 *
 * @author fengshuonan
 * @date 2018-01-11 21:29
 */
@Configuration
public class DefaultAuthConfig {

    /**
     * 调用当前登录用户的工具类
     */
    @Bean
    public UserContext userContext() {
        return new UserContext();
    }
}
