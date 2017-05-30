package com.stylefeng.roses.facade.enums;

/**
 * 消息状态枚举
 *
 * @author stylefeng
 * @Date 2017/5/30 22:21
 */
public enum MessageStatusEnum {

    WAITING_CONFIRM("待确认"),

    SENDING("发送中");

    private String desc;

    MessageStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
