package com.stylefeng.roses;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.stylefeng.roses.base.BaseJunit;
import com.stylefeng.roses.persistence.dao.MessageMapper;
import com.stylefeng.roses.persistence.model.Message;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 测试消息读取
 *
 * @author fengshuonan
 * @date 2017-05-29 21:36
 */
public class ModelTest extends BaseJunit {

    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void modelTest() {
        Message message = new Message();
        message.setVersion(1);
        message.setCreateTime(new Date());
        message.setMessageId(IdWorker.get32UUID());
        message.setMessageBody("body");
        message.setConsumerQueue("queue1");
        message.setMessageSendTimes(0);
        message.setAreadlyDead("no");
        message.setStatus("in");
        messageMapper.insert(message);
    }

}
