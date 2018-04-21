package com.stylefeng.roses.gate.error;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.roses.api.common.exception.ServiceException;
import com.xiaoleilu.hutool.io.IoUtil;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

import static com.stylefeng.roses.api.common.exception.CoreExceptionEnum.IO_ERROR;

/**
 * roses自己的feign错误解码器（为了feign接收到错误的返回，转化成roses可识别的ServiceException）
 *
 * @author stylefeng
 * @Date 2018/4/20 23:14
 */
public class RosesErrorFeignDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String resposeBody;
        try {
            resposeBody = IoUtil.read(response.body().asInputStream(), "UTF-8");
        } catch (IOException e) {
            return new ServiceException(IO_ERROR);
        }
        JSONObject parse = JSON.parseObject(resposeBody);
        Integer code = parse.getInteger("code");
        String message = parse.getString("message");
        if (code == null) {
            Integer status = parse.getInteger("status");
            return new ServiceException(status, message);
        } else {
            return new ServiceException(code, message);
        }
    }
}