package com.stylefeng.roses.gate.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.stylefeng.roses.api.common.exception.CoreExceptionEnum;
import com.stylefeng.roses.api.common.exception.ServiceException;
import com.stylefeng.roses.core.base.response.ErrorResponseData;
import com.xiaoleilu.hutool.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;
import java.util.Map;

/**
 * 错误信息提示的配置
 *
 * @author fengshuonan
 * @date 2017-11-14-下午5:56
 */
@Configuration
public class ErrorTipConfig extends WebMvcConfigurationSupport {

    @Autowired
    private FastJsonHttpMessageConverter fastJsonHttpMessageConverter;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(fastJsonHttpMessageConverter);
    }

    @Bean
    public RosesErrorAttributes rosesErrorAttributes() {
        return new RosesErrorAttributes();
    }

    /**
     * 处理zuul过滤器中的异常响应
     *
     * @author fengshuonan
     * @Date 2018/5/8 下午6:26
     */
    public class RosesErrorAttributes extends DefaultErrorAttributes {


        @Override
        public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
            RequestContext currentContext = RequestContext.getCurrentContext();
            Throwable throwable = currentContext.getThrowable();
            if (throwable != null && throwable instanceof ZuulException) {
                ZuulException zuulException = (ZuulException) throwable;
                Throwable cause = zuulException.getCause();
                if (cause != null && cause instanceof ServiceException) {
                    ServiceException serviceException = (ServiceException) cause;
                    return BeanUtil.beanToMap(new ErrorResponseData(serviceException.getCode(), serviceException.getMessage(), null));
                }
            }

            return BeanUtil.beanToMap(new ErrorResponseData(CoreExceptionEnum.SERVICE_ERROR.getCode(), CoreExceptionEnum.SERVICE_ERROR.getMessage(), null));
        }

    }
}
