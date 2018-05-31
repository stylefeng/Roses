package com.stylefeng.roses.core.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.stylefeng.roses.core.base.controller.GlobalErrorView;
import com.stylefeng.roses.core.converter.RequestDataMessageConvert;
import com.stylefeng.roses.core.util.MvcAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class DefaultWebConfig extends WebMvcConfigurationSupport {

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


    //@Autowired
    //private RequestMappingHandlerAdapter handlerAdapter;
    //
    //@PostConstruct
    //public void addConversionConfig() {
    //    ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
    //    if (initializer.getConversionService() != null) {
    //        GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
    //        genericConversionService.addConverter(new StringToDateConverter());
    //    }
    //}
    //
    //public class StringToDateConverter implements Converter<String, Date> {
    //    @Override
    //    public Date convert(String dateString) {
    //        return DateUtil.parse(dateString);
    //    }
    //}
}


