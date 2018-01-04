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
        super(true, SUCCESS_MESSAGE, object);
    }

    public SuccessResponse(String message, Object object) {
        super(true, message, object);
    }
}
