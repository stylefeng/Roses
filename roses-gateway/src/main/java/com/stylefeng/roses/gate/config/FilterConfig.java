package com.stylefeng.roses.gate.config;

import com.stylefeng.roses.gate.filter.DevelopFilter;
import com.stylefeng.roses.gate.filter.JwtTokenFilter;
import com.stylefeng.roses.gate.filter.PathMatchFilter;
import com.stylefeng.roses.gate.filter.cors.DLCorsRegistration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistration;

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


    /**
     * 跨域配置
     */
    @Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
        // 对响应头进行CORS授权
        DLCorsRegistration corsRegistration = new DLCorsRegistration("/**");
        this._configCorsParams(corsRegistration);

        // 注册CORS过滤器
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", corsRegistration.getCorsConfiguration());
        CorsFilter corsFilter = new CorsFilter(configurationSource);
        return new FilterRegistrationBean(corsFilter);
    }

    private void _configCorsParams(CorsRegistration corsRegistration) {
        corsRegistration.allowedOrigins("*")
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name())
                .allowedHeaders("*")
                .exposedHeaders("Set-Cookie")
                .allowCredentials(true)
                .maxAge(1800L);
    }
}
