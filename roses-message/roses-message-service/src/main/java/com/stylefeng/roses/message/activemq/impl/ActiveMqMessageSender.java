package com.stylefeng.roses.message.activemq.impl;

import com.stylefeng.roses.api.common.exception.CoreExceptionEnum;
import com.stylefeng.roses.api.common.exception.ServiceException;
import com.stylefeng.roses.api.message.model.ReliableMessage;
import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.message.activemq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * activemq实现的消息发送器
 *
 * @author fengshuonan
 * @date 2018-04-22 22:03
 */
public class ActiveMqMessageSender implements MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(ReliableMessage reliableMessage) {

        if (reliableMessage == null ||
                ToolUtil.isOneEmpty(reliableMessage.getConsumerQueue(), reliableMessage.getMessageBody())) {
            throw new ServiceException(CoreExceptionEnum.REQUEST_NULL);
        }

        jmsTemplate.setDefaultDestinationName(reliableMessage.getConsumerQueue());
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(reliableMessage.getMessageBody());
            }
        });
    }
}
