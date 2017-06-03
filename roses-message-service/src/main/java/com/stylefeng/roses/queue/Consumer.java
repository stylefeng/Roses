package com.stylefeng.roses.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息的消费者
 *
 * @author fengshuonan
 * @Date 2017/6/3 14:01
 */
@Component
public class Consumer {

    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 14:01
     */
    @JmsListener(destination = "orderQueue")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:" + text);
    }
} 