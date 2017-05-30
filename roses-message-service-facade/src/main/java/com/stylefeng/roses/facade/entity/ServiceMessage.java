package com.stylefeng.roses.facade.entity;

import java.io.Serializable;

/**
 * 持久化消息实体
 *
 * @author stylefeng
 * @Date 2017/5/30 22:20
 */
public class ServiceMessage implements Serializable{

    private String messageId;

    private String messageBody;

    private String messageDataType;

    private String consumerQueue;

    private Integer messageSendTimes;

    private String areadlyDead;

    private String field1;

    private String field2;

    private String field3;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageDataType() {
        return messageDataType;
    }

    public void setMessageDataType(String messageDataType) {
        this.messageDataType = messageDataType;
    }

    public String getConsumerQueue() {
        return consumerQueue;
    }

    public void setConsumerQueue(String consumerQueue) {
        this.consumerQueue = consumerQueue;
    }

    public Integer getMessageSendTimes() {
        return messageSendTimes;
    }

    public void setMessageSendTimes(Integer messageSendTimes) {
        this.messageSendTimes = messageSendTimes;
    }

    public String getAreadlyDead() {
        return areadlyDead;
    }

    public void setAreadlyDead(String areadlyDead) {
        this.areadlyDead = areadlyDead;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}