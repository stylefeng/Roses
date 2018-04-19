package com.stylefeng.roses.api.message.exception;


import com.stylefeng.roses.api.common.AbstractServiceException;

/**
 * 消息服务异常集合
 *
 * @author stylefeng
 * @Date 2018年4月18日 22:51:31
 */
public enum MessageExceptionEnum implements AbstractServiceException {


    QUEUE_CANT_EMPTY(600, "消息队列不能为空"),
    MESSAGE_ID_CANT_EMPTY(601, "消息id不能为空"),
    MESSAGE_BODY_CANT_EMPTY(602, "消息body不能为空");

    MessageExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
