package com.stylefeng.roses.core.base.response;


/**
 * 返回给前台的通用包装
 *
 * @author stylefeng
 * @Date 2018/1/4 22:37
 */
public class JsonResponse {

    public static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";

    public static final String DEFAULT_ERROR_MESSAGE = "网络异常";

    public static final Integer DEFAULT_SUCCESS_CODE = 200;

    public static final Integer DEFAULT_ERROR_CODE = 500;

    /**
     * 请求是否成功
     */
    private Boolean success;

    /**
     * 响应状态码
     */
    private Integer code;

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

    public JsonResponse(Boolean success, Integer code, String message, Object object) {
        this.success = success;
        this.code = code;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static SuccessResponse success(Object object) {
        return new SuccessResponse(object);
    }

    public static SuccessResponse success(Integer code, String message, Object object) {
        return new SuccessResponse(code, message, object);
    }

    public static ErrorResponse error(String message) {
        return new ErrorResponse(message);
    }

    public static ErrorResponse error(Integer code, String message) {
        return new ErrorResponse(code, message);
    }

    public static ErrorResponse error(Integer code, String message, Object object) {
        return new ErrorResponse(code, message, object);
    }
}
