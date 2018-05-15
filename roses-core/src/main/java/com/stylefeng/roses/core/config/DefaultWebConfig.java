package com.stylefeng.roses.core.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.stylefeng.roses.core.base.controller.GlobalErrorView;
import com.stylefeng.roses.core.converter.RequestDataMessageConvert;
import com.stylefeng.roses.core.util.MvcAdapter;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.Date;

@Configuration
public class DefaultWebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @PostConstruct
    public void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new StringToDateConverter());
        }
    }

    public class StringToDateConverter implements Converter<String, Date> {
        @Override
        public Date convert(String dateString) {
            return DateUtil.parse(dateString);
        }
    }

    @Bean("error")
    public GlobalErrorView error() {
        return new GlobalErrorView();
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(
            FastJsonHttpMessageConverter fastJsonHttpMessageConverter,
            RequestDataMessageConvert requestDataMessageConvert) {

        return MvcAdapter.requestMappingHandlerAdapter(
                super.requestMappingHandlerAdapter(), fastJsonHttpMessageConverter, requestDataMessageConvert);
    }

    @Bean
    public RequestDataMessageConvert requestDataMessageConvert() {
        return new RequestDataMessageConvert();
    }
}
