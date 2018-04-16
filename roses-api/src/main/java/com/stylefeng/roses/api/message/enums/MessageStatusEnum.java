package com.stylefeng.roses.api.message.enums;

/**
 * 消息状态
 *
 * @author stylefeng
 * @Date 2018/4/16 22:31
 */
public enum MessageStatusEnum {

    WAIT_VERIFY("待确认"),

    SENDING("发送中");

    private String desc;

    MessageStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
