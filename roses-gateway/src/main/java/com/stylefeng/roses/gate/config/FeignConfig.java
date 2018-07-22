package com.stylefeng.roses.gate.config;

import com.stylefeng.roses.core.feign.RosesFeignErrorDecoder;
import com.stylefeng.roses.core.feign.RosesFeignHeaderProcessInterceptor;
import feign.Feign;
import feign.RequestInterceptor;
import feign.hystrix.HystrixFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * feign的错误编码配置（为了feign接收到错误的返回，转化成roses可识别的ServiceException）
 *
 * @author stylefeng
 * @Date 2018/4/20 23:11
 */
@Configuration
public class FeignConfig {

    /**
     * roses自定义错误解码器
     */
    @Bean
    @Scope("prototype")
    public Feign.Builder feignHystrixBuilder() {
        return HystrixFeign.builder().errorDecoder(new RosesFeignErrorDecoder());
    }

    /**
     * feign请求加上当前请求接口的headers
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RosesFeignHeaderProcessInterceptor();
    }
}