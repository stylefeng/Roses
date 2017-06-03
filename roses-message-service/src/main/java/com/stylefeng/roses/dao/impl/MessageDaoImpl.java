package com.stylefeng.roses.dao.impl;

import com.stylefeng.roses.dao.MessageDao;
import com.stylefeng.roses.persistence.dao.MessageMapper;
import com.stylefeng.roses.persistence.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息dao
 *
 * @author fengshuonan
 * @date 2017-06-03 13:43
 */
@Component
public class MessageDaoImpl implements MessageDao{

    @Autowired
    MessageMapper messageMapper;

    @Override
    public Message getMessageByMessageId(String messageId) {
        Message message = new Message();
        message.setMessageId(messageId);
        return messageMapper.selectOne(message);
    }
}
