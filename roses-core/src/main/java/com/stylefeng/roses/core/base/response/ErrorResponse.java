package com.stylefeng.roses.core.base.response;


/**
 * 请求失败的返回
 *
 * @author stylefeng
 * @Date 2018/1/4 22:39
 */
public class ErrorResponse extends JsonResponse {

    public ErrorResponse() {
    }

    public ErrorResponse(String message) {
        super(false, DEFAULT_ERROR_CODE, message, null);
    }

    public ErrorResponse(Integer code, String message) {
        super(false, code, message, null);
    }

    public ErrorResponse(Integer code, String message, Object object) {
        super(false, code, message, object);
    }
}
