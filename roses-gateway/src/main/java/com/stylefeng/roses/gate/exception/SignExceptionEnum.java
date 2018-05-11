package com.stylefeng.roses.gate.exception;

import com.stylefeng.roses.api.common.AbstractServiceException;

/**
 * 签名异常
 *
 * @author fengshuonan
 * @date 2018-01-05 14:48
 */
public enum SignExceptionEnum implements AbstractServiceException {

    ACCOUNT_OR_PASSOWD_WRONG(602, "账号或密码错误"),
    TOKEN_ERROR(603, "token无效"),
    NO_PERMISSION(604, "没有访问该资源的权限"),
    SIGN_ERROR(605, "数据签名错误"),
    SIGN_ERROR_WITH_EMPTY_DATA(606, "数据签名错误,存在为空的参数"),
    GET_SECRET_ERROR(607, "找不到本应用失败");

    SignExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
