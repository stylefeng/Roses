package com.stylefeng.roses.dao;

import com.stylefeng.roses.persistence.model.Message;

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
}
