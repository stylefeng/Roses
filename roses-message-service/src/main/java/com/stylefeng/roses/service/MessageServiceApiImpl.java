package com.stylefeng.roses.service;


import com.stylefeng.roses.dao.MessageDao;
import com.stylefeng.roses.facade.api.MessageServiceApi;
import com.stylefeng.roses.facade.entity.ServiceMessage;
import com.stylefeng.roses.facade.enums.MsgServiceExceptionEnum;
import com.stylefeng.roses.facade.enums.MsgStatusEnum;
import com.stylefeng.roses.facade.exception.MsgServiceException;
import com.stylefeng.roses.factory.MessageFactory;
import com.stylefeng.roses.persistence.model.Message;
import com.stylefeng.roses.queue.Producer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.stylefeng.roses.core.utils.ToolUtil.assertEmpty;

/**
 * 消息服务子系统接口的实现
 *
 * @author fengshuonan
 * @date 2017-05-29 22:44
 */
@Service
@Transactional
public class MessageServiceApiImpl implements MessageServiceApi {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    Producer producer;

    @Override
    public void saveMessageWaitingConfirm(ServiceMessage serviceMessage) {
        assertEmpty(serviceMessage,new MsgServiceException(MsgServiceExceptionEnum.REQUEST_NULL));
        assertEmpty(serviceMessage.getConsumerQueue(),new MsgServiceException(MsgServiceExceptionEnum.QUEUE_CANT_BE_NULL));

        Message message = MessageFactory.createPreMessage(serviceMessage);
        message.insert();
    }

    @Override
    public void confirmAndSendMessage(String messageId) throws MsgServiceException {
        assertEmpty(messageId,new MsgServiceException(MsgServiceExceptionEnum.REQUEST_NULL));

        Message message = messageDao.getMessageByMessageId(messageId);
        assertEmpty(message,new MsgServiceException(MsgServiceExceptionEnum.CANT_FIND_THIS_MESSAGE));

        message.setStatus(MsgStatusEnum.SENDING.name());
        message.setEditTime(new Date());
        message.updateById();

        producer.sendMessage(message.getConsumerQueue(),message.getMessageBody());
    }

    @Override
    public void saveAndSendMessage(ServiceMessage serviceMessage) throws MsgServiceException {
        assertEmpty(serviceMessage,new MsgServiceException(MsgServiceExceptionEnum.REQUEST_NULL));
        assertEmpty(serviceMessage.getConsumerQueue(),new MsgServiceException(MsgServiceExceptionEnum.QUEUE_CANT_BE_NULL));

        Message sendingMessage = MessageFactory.createSendingMessage(serviceMessage);

        producer.sendMessage(sendingMessage.getConsumerQueue(),sendingMessage.getMessageBody());
    }

    @Override
    public void directSendMessage(ServiceMessage serviceMessage) throws MsgServiceException {
        assertEmpty(serviceMessage,new MsgServiceException(MsgServiceExceptionEnum.REQUEST_NULL));
        assertEmpty(serviceMessage.getConsumerQueue(),new MsgServiceException(MsgServiceExceptionEnum.QUEUE_CANT_BE_NULL));

        producer.sendMessage(serviceMessage.getConsumerQueue(),serviceMessage.getMessageBody());
    }

    @Override
    public void reSendMessage(ServiceMessage serviceMessage) throws MsgServiceException {
        assertEmpty(serviceMessage,new MsgServiceException(MsgServiceExceptionEnum.REQUEST_NULL));
        assertEmpty(serviceMessage.getConsumerQueue(),new MsgServiceException(MsgServiceExceptionEnum.QUEUE_CANT_BE_NULL));

        serviceMessage.addTimes();
        Message message = MessageFactory.createBaseMessage();
        BeanUtils.copyProperties(serviceMessage,message);

        message.update("messageId = ?", message.getMessageId());

        producer.sendMessage(message.getConsumerQueue(),message.getMessageBody());
    }


}
