package com.stylefeng.roses.core.util;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.stylefeng.roses.core.converter.RequestDataMessageConvert;
import com.stylefeng.roses.core.converter.RequestDataTypeMethodProcessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 统一请求转化器默认配置
 *
 * @author fengshuonan
 * @Date 2018/2/24 14:39
 */
public class MvcAdapter {

    public static RequestMappingHandlerAdapter requestMappingHandlerAdapter(
            RequestMappingHandlerAdapter original,
            FastJsonHttpMessageConverter fastJsonHttpMessageConverter,
            RequestDataMessageConvert requestDataMessageConvert) {

        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(requestDataMessageConvert);

        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>();
        argumentResolvers.add(new RequestDataTypeMethodProcessor(converters));
        original.setCustomArgumentResolvers(argumentResolvers);

        List<HttpMessageConverter<?>> fastjson = new ArrayList<>();
        fastjson.add(fastJsonHttpMessageConverter);
        original.setMessageConverters(fastjson);
        return original;
    }
}