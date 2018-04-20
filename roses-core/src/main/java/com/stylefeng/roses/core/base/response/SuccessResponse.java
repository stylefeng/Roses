package com.stylefeng.roses.core.base.response;

/**
 * 请求成功的返回
 *
 * @author stylefeng
 * @Date 2018/1/4 22:38
 */
public class SuccessResponse extends JsonResponse {

    public SuccessResponse() {
    }

    public SuccessResponse(Object object) {
        super(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, object);
    }

    public SuccessResponse(Integer code, String message, Object object) {
        super(true, code, message, object);
    }
}
