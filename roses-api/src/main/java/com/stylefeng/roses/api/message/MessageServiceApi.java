package com.stylefeng.roses.api.message;


import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.roses.api.message.model.ReliableMessage;

import java.util.Map;


/**
 * 消息服务的接口
 *
 * @author stylefeng
 * @Date 2018/4/16 21:47
 */
public interface MessageServiceApi {

    /**
     * 预存储消息.
     */
    void preStoreMessage(ReliableMessage reliableMessage);

    /**
     * 确认并发送消息.
     */
    void confirmAndSendMessage(String messageId);

    /**
     * 存储并发送消息.
     */
    int saveAndSendMessage(ReliableMessage reliableMessage);

    /**
     * 直接发送消息.
     */
    void directSendMessage(ReliableMessage reliableMessage);

    /**
     * 重发消息.
     */
    void reSendMessage(ReliableMessage reliableMessage);

    /**
     * 根据messageId重发某条消息.
     */
    void reSendMessageByMessageId(String messageId);

    /**
     * 将消息标记为死亡消息.
     */
    void setMessageToAreadlyDead(String messageId);

    /**
     * 根据消息ID获取消息
     */
    ReliableMessage getMessageByMessageId(String messageId);

    /**
     * 根据消息ID删除消息
     */
    void deleteMessageByMessageId(String messageId);

    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    void reSendAllDeadMessageByQueueName(String queueName, int batchSize);

    /**
     * 获取分页数据
     */
    Page listPage(Page pageParam, Map<String, Object> paramMap);

}
