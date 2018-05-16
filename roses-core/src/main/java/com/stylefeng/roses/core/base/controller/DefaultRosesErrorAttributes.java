package com.stylefeng.roses.core.base.controller;

import com.stylefeng.roses.api.common.exception.CoreExceptionEnum;
import com.stylefeng.roses.core.base.response.ResponseData;
import com.xiaoleilu.hutool.bean.BeanUtil;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * 重写spring得默认响应提示信息
 *
 * @author fengshuonan
 * @date 2017-11-14-下午5:54
 */
public class DefaultRosesErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        return BeanUtil.beanToMap(ResponseData.error(CoreExceptionEnum.SERVICE_ERROR.getMessage()));
    }
}
