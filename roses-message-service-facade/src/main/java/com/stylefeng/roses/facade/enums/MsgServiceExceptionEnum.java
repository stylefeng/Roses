package com.stylefeng.roses.facade.enums;

/**
 * @author fengshuonan
 * @Description 所有业务异常的枚举
 * @date 2016年11月12日 下午5:04:51
 */
public enum MsgServiceExceptionEnum {

    /**
     * 错误的请求
     */
    REQUEST_NULL(400, "请求有错误"),
    QUEUE_CANT_BE_NULL(401, "消息的消费队列不能为空");

    MsgServiceExceptionEnum(int code, String message) {
        this.friendlyCode = code;
        this.friendlyMsg = message;
    }

    private int friendlyCode;

    private String friendlyMsg;

    public int getCode() {
        return friendlyCode;
    }

    public void setCode(int code) {
        this.friendlyCode = code;
    }

    public String getMessage() {
        return friendlyMsg;
    }

    public void setMessage(String message) {
        this.friendlyMsg = message;
    }

}
