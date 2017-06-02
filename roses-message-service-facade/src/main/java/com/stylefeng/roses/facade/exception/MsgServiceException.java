package com.stylefeng.roses.facade.exception;

import com.stylefeng.roses.facade.enums.MsgServiceExceptionEnum;

/**
 * 消息子系统业务异常
 *
 * @author fengshuonan
 * @date 2017-06-02 23:07
 */
public class MsgServiceException extends RuntimeException {

    //友好提示的code码
    private int friendlyCode;

    //友好提示
    private String friendlyMsg;

    public MsgServiceException(MsgServiceExceptionEnum msgServiceExceptionEnum) {
        this.friendlyCode = msgServiceExceptionEnum.getCode();
        this.friendlyMsg = msgServiceExceptionEnum.getMessage();
    }

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
