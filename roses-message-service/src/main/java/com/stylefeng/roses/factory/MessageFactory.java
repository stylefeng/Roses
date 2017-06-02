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
     * 创建预存储消息
     *
     * @author stylefeng
     * @Date 2017/6/2 23:54
     */
    public static Message createPreMessage(ServiceMessage serviceMessage) {
        Message message = new Message();
        message.setStatus("0");
        message.setEditor("stylefeng");
        message.setCreater("stylefeng");
        message.setCreateTime(new Date());
        message.setEditTime(new Date());
        message.setStatus(MsgStatusEnum.WAITING_CONFIRM.name());
        message.setAreadlyDead(IsOrNot.NO.name());
        message.setMessageSendTimes(0);
        BeanUtils.copyProperties(serviceMessage,message);
        return message;
    }
}
