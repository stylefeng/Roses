package com.stylefeng.roses.core.config;

import com.stylefeng.roses.core.exception.DefualtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 保留类，如果控制器需要些aop在这里写
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午4:48:10
 */
@Configuration
@EnableAspectJAutoProxy
public class DefaultAopConfig {

    /**
     * 默认的异常拦截器
     */
    @Bean
    public DefualtExceptionHandler defaultControllerExceptionHandler() {
        return new DefualtExceptionHandler();
    }

}