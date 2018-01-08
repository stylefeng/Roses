package com.stylefeng.roses.core.base.response;


/**
 * 返回给前台的通用包装
 *
 * @author stylefeng
 * @Date 2018/1/4 22:37
 */
public class JsonResponse {

    public static final String SUCCESS_MESSAGE = "请求成功";

    public static final String ERROR_MESSAGE = "网络异常";

    /**
     * 请求是否成功
     */
    private Boolean success;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应对象
     */
    private Object object;

    public JsonResponse() {
    }

    public JsonResponse(Boolean success, String message, Object object) {
        this.success = success;
        this.message = message;
        this.object = object;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static SuccessResponse success(String message, Object object) {
        return new SuccessResponse(message, object);
    }

    public static SuccessResponse successResponse(Object object) {
        return new SuccessResponse(object);
    }

    public static ErrorResponse error(String message) {
        return new ErrorResponse(message);
    }

    public static ErrorResponse error(Object object) {
        return new ErrorResponse(object);
    }

    public static ErrorResponse error(String message, Object object) {
        return new ErrorResponse(message, object);
    }
}
