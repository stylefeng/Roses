package com.stylefeng.roses.dao.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.roses.core.enums.IsOrNot;
import com.stylefeng.roses.dao.MessageDao;
import com.stylefeng.roses.persistence.dao.MessageMapper;
import com.stylefeng.roses.persistence.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 消息dao
 *
 * @author fengshuonan
 * @date 2017-06-03 13:43
 */
@Component
public class MessageDaoImpl implements MessageDao {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public Message getMessageByMessageId(String messageId) {
        Message message = new Message();
        message.setMessageId(messageId);
        return messageMapper.selectOne(message);
    }

    @Override
    public void deleteByMessageId(String messageId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("message_id", messageId);
        messageMapper.deleteByMap(map);
    }

    @Override
    public List<Message> findAllDeadMessageByQueue(String queueName){
        Wrapper<Message> wrapper = new EntityWrapper<>();
        wrapper = wrapper.eq("consumer_queue",queueName).and().eq("areadly_dead", IsOrNot.YES.name()).orderBy("create_time",false);
        return messageMapper.selectList(wrapper);
    }
}
