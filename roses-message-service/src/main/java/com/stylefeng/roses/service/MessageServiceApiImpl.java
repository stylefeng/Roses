package com.stylefeng.roses.service;


import com.stylefeng.roses.config.properties.RosesProperties;
import com.stylefeng.roses.core.enums.IsOrNot;
import com.stylefeng.roses.dao.MessageDao;
import com.stylefeng.roses.facade.api.MessageServiceApi;
import com.stylefeng.roses.facade.entity.ServiceMessage;
import com.stylefeng.roses.facade.enums.MsgServiceExceptionEnum;
import com.stylefeng.roses.facade.enums.MsgStatusEnum;
import com.stylefeng.roses.facade.exception.MsgServiceException;
import com.stylefeng.roses.factory.MessageFactory;
import com.stylefeng.roses.persistence.model.Message;
import com.stylefeng.roses.queue.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static com.stylefeng.roses.core.utils.ToolUtil.assertEmpty;

/**
 * 消息服务子系统接口的实现
 *
 * @author fengshuonan
 * @date 2017-05-29 22:44
 */
@RestController
public class MessageServiceApiImpl implements MessageServiceApi {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    Producer producer;

    @Autowired
    RosesProperties rosesProperties;

    @Override
    public void saveMessageWaitingConfirm(@RequestBody ServiceMessage serviceMessage) {
        assertEmpty(serviceMessage, new MsgServiceException(MsgServiceExceptionEnum.REQUEST_NULL));
        assertEmpty(serviceMessage.getConsumerQueue(), new MsgServiceException(MsgServiceExceptionEnum.QUEUE_CANT_BE_NULL));

        Message message = MessageFactory.createPreMessage(serviceMessage);
        message.insert();
    }

    @Override
    public void confirmAndSendMessage(@PathVariable String messageId) throws MsgServiceException {
        assertEmpty(messageId, new MsgServiceException(MsgServiceExceptionEnum.REQUEST_NULL));

        Message message = messageDao.getMessageByMessageId(messageId);
        assertEmpty(message, new MsgServiceException(MsgServiceExceptionEnum.CANT_FIND_THIS_MESSAGE));

        message.setStatus(MsgStatusEnum.SENDING.name());
        message.setEditTime(new Date());
        message.updateById();

        producer.sendMessage(message.getConsumerQueue(), message.getMessageBody());
    }

    @Override
    public void saveAndSendMessage(@RequestBody ServiceMessage serviceMessage) throws MsgServiceException {
        assertEmpty(serviceMessage, new MsgServiceException(MsgServiceExceptionEnum.REQUEST_NULL));
        assertEmpty(serviceMessage.getConsumerQueue(), new MsgServiceException(MsgServiceExceptionEnum.QUEUE_CANT_BE_NULL));

        Message sendingMessage = MessageFactory.createSendingMessage(serviceMessage);
        sendingMessage.insert();

        producer.sendMessage(sendingMessage.getConsumerQueue(), sendingMessage.getMessageBody());
    }

    @Override
    public void directSendMessage(@RequestBody ServiceMessage serviceMessage) throws MsgServiceException {
        assertEmpty(serviceMessage, new MsgServiceException(MsgServiceExceptionEnum.REQUEST_NULL));
        assertEmpty(serviceMessage.getConsumerQueue(), new MsgServiceException(MsgServiceExceptionEnum.QUEUE_CANT_BE_NULL));

        producer.sendMessage(serviceMessage.getConsumerQueue(), serviceMessage.getMessageBody());
    }

    @Override
    public void reSendMessage(@RequestBody ServiceMessage serviceMessage) throws MsgServiceException {
        assertEmpty(serviceMessage, new MsgServiceException(MsgServiceExceptionEnum.REQUEST_NULL));
        assertEmpty(serviceMessage.getConsumerQueue(), new MsgServiceException(MsgServiceExceptionEnum.QUEUE_CANT_BE_NULL));

        Message message = messageDao.getMessageByMessageId(serviceMessage.getMessageId());
        assertEmpty(message, new MsgServiceException(MsgServiceExceptionEnum.CANT_FIND_THIS_MESSAGE));

        serviceMessage.addTimes();
        message.setMessageSendTimes(serviceMessage.getMessageSendTimes());
        message.setEditTime(new Date());

        if (message.getMessageSendTimes() >= rosesProperties.getMessageMaxSendTimes()) {
            message.setAreadlyDead(IsOrNot.YES.name());
        }

        message.updateById();

        producer.sendMessage(message.getConsumerQueue(), message.getMessageBody());
    }

    @Override
    public void reSendMessageByMessageId(@PathVariable String messageId) throws MsgServiceException {
        Message message = messageDao.getMessageByMessageId(messageId);
        assertEmpty(message, new MsgServiceException(MsgServiceExceptionEnum.CANT_FIND_THIS_MESSAGE));

        if (message.getMessageSendTimes() >= rosesProperties.getMessageMaxSendTimes()) {
            message.setAreadlyDead(IsOrNot.YES.name());
        }

        message.setEditTime(new Date());
        message.setMessageSendTimes(message.getMessageSendTimes() + 1);
        message.updateById();

        producer.sendMessage(message.getConsumerQueue(), message.getMessageBody());
    }

    @Override
    public void deleteMessageByMessageId(@PathVariable String messageId) throws MsgServiceException {
        assertEmpty(messageId, new MsgServiceException(MsgServiceExceptionEnum.REQUEST_NULL));
        messageDao.deleteByMessageId(messageId);
    }

    @Override
    public void reSendAllDeadMessageByQueueName(@PathVariable String queue) {
        assertEmpty(queue, new MsgServiceException(MsgServiceExceptionEnum.QUEUE_CANT_BE_NULL));
        List<Message> allDeadMessage = messageDao.findAllDeadMessageByQueue(queue);
        for (Message message : allDeadMessage) {
            message.setEditTime(new Date());
            message.setMessageSendTimes(message.getMessageSendTimes() + 1);
            message.updateById();

            producer.sendMessage(queue, message.getMessageBody());
        }
    }


}
