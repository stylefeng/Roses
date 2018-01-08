package com.stylefeng.roses.gate.error;

import com.stylefeng.roses.core.base.response.JsonResponse;
import com.stylefeng.roses.core.exception.CoreExceptionEnum;
import com.xiaoleilu.hutool.bean.BeanUtil;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * 默认提示信息
 *
 * @author fengshuonan
 * @date 2017-11-14-下午5:54
 */
public class DefaultRosesErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        return BeanUtil.beanToMap(JsonResponse.error(CoreExceptionEnum.SERVICE_ERROR.getMessage()));
    }
}
