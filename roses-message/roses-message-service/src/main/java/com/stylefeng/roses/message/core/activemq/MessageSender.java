package com.stylefeng.roses.message.core.activemq;

import com.stylefeng.roses.api.message.model.ReliableMessage;

/**
 * mq消息队列的发送者（若想拓展mq，则可实现此接口，并在configuration中配置即可）
 *
 * @author fengshuonan
 * @date 2018-04-22 21:55
 */
public interface MessageSender {

    /**
     * 发送消息
     *
     * @author stylefeng
     * @Date 2018/4/22 22:02
     */
    void sendMessage(ReliableMessage reliableMessage);
}
