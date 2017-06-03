package com.stylefeng.roses.factory;

import com.stylefeng.roses.core.enums.IsOrNot;
import com.stylefeng.roses.facade.entity.ServiceMessage;
import com.stylefeng.roses.facade.enums.MsgStatusEnum;
import com.stylefeng.roses.persistence.model.Message;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 消息创建工厂
 *
 * @author fengshuonan
 * @date 2017-06-02 23:13
 */
public class MessageFactory {

    /**
     * 创建基础消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 14:16
     */
    public static Message createBaseMessage(){
        Message message = new Message();
        message.setStatus("0");
        message.setEditor("stylefeng");
        message.setCreater("stylefeng");
        message.setCreateTime(new Date());
        message.setEditTime(new Date());
        message.setAreadlyDead(IsOrNot.NO.name());
        return message;
    }

    /**
     * 创建预存储消息
     *
     * @author stylefeng
     * @Date 2017/6/2 23:54
     */
    public static Message createPreMessage(ServiceMessage serviceMessage) {
        Message message = createBaseMessage();
        message.setStatus(MsgStatusEnum.WAITING_CONFIRM.name());
        message.setMessageSendTimes(0);
        BeanUtils.copyProperties(serviceMessage,message);
        return message;
    }

    /**
     * 创建发送中的消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 14:13
     */
    public static Message createSendingMessage(ServiceMessage serviceMessage){
        Message message = createBaseMessage();
        message.setStatus(MsgStatusEnum.SENDING.name());
        message.setMessageSendTimes(0);
        BeanUtils.copyProperties(serviceMessage,message);
        return message;
    }
}
