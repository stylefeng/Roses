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
        super(false, message, null);
    }

    public ErrorResponse(Object object) {
        super(false, ERROR_MESSAGE, object);
    }

    public ErrorResponse(String message, Object object) {
        super(false, message, object);
    }
}
