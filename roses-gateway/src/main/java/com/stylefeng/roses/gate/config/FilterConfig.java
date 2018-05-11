package com.stylefeng.roses.gate.config;

import com.stylefeng.roses.gate.filter.DevelopFilter;
import com.stylefeng.roses.gate.filter.JwtTokenFilter;
import com.stylefeng.roses.gate.filter.PathMatchFilter;
import com.stylefeng.roses.gate.filter.RequestNoGenerateFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器的配置
 *
 * @author fengshuonan
 * @date 2017-11-08-下午3:23
 */
@Configuration
public class FilterConfig {

    @Bean
    @ConditionalOnProperty(prefix = "develop", name = "open", havingValue = "false", matchIfMissing = true)
    public JwtTokenFilter authFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    @ConditionalOnProperty(prefix = "develop", name = "open", havingValue = "false", matchIfMissing = true)
    public PathMatchFilter pathMatchFilter() {
        return new PathMatchFilter();
    }

    @Bean
    @ConditionalOnProperty(prefix = "develop", name = "open", havingValue = "true")
    public DevelopFilter developFilter() {
        return new DevelopFilter();
    }

    @Bean
    public RequestNoGenerateFilter requestNoGenerateFilter() {
        return new RequestNoGenerateFilter();
    }
}
