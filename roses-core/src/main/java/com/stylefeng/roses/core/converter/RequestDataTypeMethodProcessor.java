package com.stylefeng.roses.core.converter;

import com.stylefeng.roses.core.base.request.RequestData;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;

/**
 * 请求转化处理器
 *
 * @author stylefeng
 * @Date 2018/5/15 22:02
 */
public class RequestDataTypeMethodProcessor extends RequestResponseBodyMethodProcessor {

    public RequestDataTypeMethodProcessor(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(RequestData.class);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return false;
    }
}

