package com.stylefeng.roses.gate.config;

import com.stylefeng.roses.gate.filter.*;
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

    /**
     * 开发过滤器（免去每次都必须传用户id）
     */
    @Bean
    @ConditionalOnProperty(prefix = "develop", name = "open", havingValue = "true")
    public DevelopFilter developFilter() {
        return new DevelopFilter();
    }

    /**
     * token过滤器，检查每次请求token是否合法
     */
    @Bean
    @ConditionalOnProperty(prefix = "develop", name = "open", havingValue = "false", matchIfMissing = true)
    public JwtTokenFilter authFilter() {
        return new JwtTokenFilter();
    }

    /**
     * 资源过滤器，检查每次请求是否有权限访问某些资源
     */
    @Bean
    @ConditionalOnProperty(prefix = "develop", name = "open", havingValue = "false", matchIfMissing = true)
    public PathMatchFilter pathMatchFilter() {
        return new PathMatchFilter();
    }

    /**
     * 签名过滤器，校验每次请求数据的内容是否签名合法
     */
    @Bean
    @ConditionalOnProperty(prefix = "develop", name = "open", havingValue = "false", matchIfMissing = true)
    public SignValidateFilter signValidateFilter() {
        return new SignValidateFilter();
    }

    /**
     * 请求唯一编号生成器，每次请求入网关时都会生成一个唯一编号，用来记录一次请求的所有日志和异常信息
     */
    @Bean
    public RequestNoGenerateFilter requestNoGenerateFilter() {
        return new RequestNoGenerateFilter();
    }
}
