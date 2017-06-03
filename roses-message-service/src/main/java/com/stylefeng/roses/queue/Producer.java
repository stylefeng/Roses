package com.stylefeng.roses.queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * 消息发送的入口,消息生产者
 *
 * @author fengshuonan
 * @Date 2017/6/3 13:51
 */
@Service("producer")
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    /**
     * 发送消息
     *
     * @param destination 发送到的队列
     * @param message     待发送的消息
     * @author fengshuonan
     * @Date 2017/6/3 13:51
     */
    public void sendMessage(Destination destination, final String message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    /**
     * 发送消息
     *
     * @param destination 发送到的队列
     * @param message     待发送的消息
     * @author fengshuonan
     * @Date 2017/6/3 13:51
     */
    public void sendMessage(String destination, final String message) {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(destination);
        jmsTemplate.convertAndSend(activeMQQueue, message);
    }


}  