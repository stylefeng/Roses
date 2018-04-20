package com.stylefeng.roses.gate.config;

import com.stylefeng.roses.gate.error.RosesErrorFeignDecoder;
import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign的错误编码配置（为了feign接收到错误的返回，转化成roses可识别的ServiceException）
 *
 * @author stylefeng
 * @Date 2018/4/20 23:11
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public Feign.Builder myFeign() {
        return Feign.builder().errorDecoder(new RosesErrorFeignDecoder());
    }
}