package com.stylefeng.roses.core.base.response;


/**
 * 请求失败的返回
 *
 * @author stylefeng
 * @Date 2018/1/4 22:39
 */
public class ErrorResponseData extends ResponseData {

    public ErrorResponseData() {
    }

    public ErrorResponseData(String message) {
        super(false, DEFAULT_ERROR_CODE, message, null);
    }

    public ErrorResponseData(Integer code, String message) {
        super(false, code, message, null);
    }

    public ErrorResponseData(Integer code, String message, Object object) {
        super(false, code, message, object);
    }
}
