package com.stylefeng.roses.gate.config;

import com.stylefeng.roses.core.base.controller.DefaultRosesErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 错误信息提示的配置
 *
 * @author fengshuonan
 * @date 2017-11-14-下午5:56
 */
@Configuration
public class ErrorTipConfig {

    @Bean
    public DefaultRosesErrorAttributes defaultRosesErrorAttributes() {
        return new DefaultRosesErrorAttributes();
    }
}
