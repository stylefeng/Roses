package com.stylefeng.roses.core.feign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.roses.api.common.exception.CoreExceptionEnum;
import com.stylefeng.roses.api.common.exception.ServiceException;
import com.xiaoleilu.hutool.io.IoUtil;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

/**
 * roses自己的feign错误解码器（为了feign接收到错误的返回，转化成share可识别的ServiceException）
 *
 * @author stylefeng
 * @Date 2018/4/20 23:14
 */
public class RosesFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String resposeBody;
        try {
            if (response == null || response.body() == null) {
                if (response != null && response.status() == 404) {
                    return new ServiceException(CoreExceptionEnum.REMOTE_SERVICE_NULL);
                } else {
                    return new ServiceException(CoreExceptionEnum.SERVICE_ERROR);
                }
            }
            resposeBody = IoUtil.read(response.body().asInputStream(), "UTF-8");
        } catch (IOException e) {
            return new ServiceException(CoreExceptionEnum.IO_ERROR);
        }
        JSONObject parse = JSON.parseObject(resposeBody);
        Integer code = parse.getInteger("code");
        String message = parse.getString("message");
        if (message == null) {
            message = CoreExceptionEnum.SERVICE_ERROR.getMessage();
        }
        if (code == null) {

            //status为spring默认返回的数据
            Integer status = parse.getInteger("status");

            if (status == null) {
                return new ServiceException(CoreExceptionEnum.SERVICE_ERROR.getCode(), message);
            } else {
                return new ServiceException(status, message);
            }
        } else {
            return new ServiceException(code, message);
        }
    }
}