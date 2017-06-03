package com.stylefeng.roses.dao;

import com.stylefeng.roses.persistence.model.Message;

import java.util.List;

/**
 * 消息dao
 *
 * @author fengshuonan
 * @date 2017-06-03 13:43
 */
public interface MessageDao {

    /**
     * 通过消息id获取消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 13:45
     */
    Message getMessageByMessageId(String messageId);

    /**
     * 删除消息通过消息id
     *
     * @author fengshuonan
     * @Date 2017/6/3 16:27
     */
    void deleteByMessageId(String messageId);

    /**
     * 获取所有的死亡消息通过队列名称
     *
     * @author fengshuonan
     * @Date 2017/6/3 17:38
     */
    List<Message> findAllDeadMessageByQueue(String queueName);
}
