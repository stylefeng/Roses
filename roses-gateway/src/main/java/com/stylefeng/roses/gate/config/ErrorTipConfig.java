package com.stylefeng.roses.gate.config;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.stylefeng.roses.api.common.exception.CoreExceptionEnum;
import com.stylefeng.roses.api.common.exception.ServiceException;
import com.stylefeng.roses.core.base.response.ErrorResponse;
import com.xiaoleilu.hutool.bean.BeanUtil;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * 错误信息提示的配置
 *
 * @author fengshuonan
 * @date 2017-11-14-下午5:56
 */
@Configuration
public class ErrorTipConfig {

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
    private class RosesErrorAttributes extends DefaultErrorAttributes {

        @Override
        public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {

            RequestContext currentContext = RequestContext.getCurrentContext();
            Throwable throwable = currentContext.getThrowable();
            if (throwable != null && throwable instanceof ZuulException) {
                ZuulException zuulException = (ZuulException) throwable;
                Throwable cause = zuulException.getCause();
                if (cause != null && cause instanceof ServiceException) {
                    ServiceException serviceException = (ServiceException) cause;
                    return BeanUtil.beanToMap(new ErrorResponse(serviceException.getCode(), serviceException.getMessage(), null));
                }
            }

            return BeanUtil.beanToMap(new ErrorResponse(CoreExceptionEnum.SERVICE_ERROR.getCode(), CoreExceptionEnum.SERVICE_ERROR.getMessage(), null));
        }

    }
}
