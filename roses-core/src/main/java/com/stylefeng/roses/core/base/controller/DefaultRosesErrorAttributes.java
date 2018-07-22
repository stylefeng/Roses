package com.stylefeng.roses.core.base.controller;

import cn.hutool.core.bean.BeanUtil;
import com.stylefeng.roses.api.common.exception.CoreExceptionEnum;
import com.stylefeng.roses.core.base.response.ResponseData;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 重写spring得默认响应提示信息
 *
 * @author fengshuonan
 * @date 2017-11-14-下午5:54
 */
public class DefaultRosesErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        return BeanUtil.beanToMap(ResponseData.error(CoreExceptionEnum.SERVICE_ERROR.getMessage()));

    }
}
