package com.stylefeng.roses.message.provider;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.roses.api.common.enums.YseOrNotEnum;
import com.stylefeng.roses.api.message.MessageServiceApi;
import com.stylefeng.roses.api.message.enums.MessageStatusEnum;
import com.stylefeng.roses.api.message.exception.MessageExceptionEnum;
import com.stylefeng.roses.api.message.model.ReliableMessage;
import com.stylefeng.roses.core.exception.CoreExceptionEnum;
import com.stylefeng.roses.core.exception.ServiceException;
import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.message.service.IReliableMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 消息服务提供接口的实现
 *
 * @author fengshuonan
 * @date 2018-04-16 22:30
 */
@Service
public class MessageServiceImpl implements MessageServiceApi {

    @Autowired
    private IReliableMessageService bizMessageService;

    @Override
    public void preStoreMessage(ReliableMessage reliableMessage) {
        if (reliableMessage == null) {
            throw new ServiceException(CoreExceptionEnum.REQUEST_NULL);
        }
        if (ToolUtil.isEmpty(reliableMessage.getConsumerQueue())) {
            throw new ServiceException(MessageExceptionEnum.QUEUE_CANT_EMPTY);
        }
        reliableMessage.setStatus(MessageStatusEnum.WAIT_VERIFY.name());
        reliableMessage.setAreadlyDead(YseOrNotEnum.N.name());
        reliableMessage.setMessageSendTimes(0);
        bizMessageService.insert(reliableMessage);
    }

    @Override
    public void confirmAndSendMessage(String messageId) {

    }

    @Override
    public int saveAndSendMessage(ReliableMessage reliableMessage) {
        return 0;
    }

    @Override
    public void directSendMessage(ReliableMessage reliableMessage) {

    }

    @Override
    public void reSendMessage(ReliableMessage reliableMessage) {

    }

    @Override
    public void reSendMessageByMessageId(String messageId) {

    }

    @Override
    public void setMessageToAreadlyDead(String messageId) {

    }

    @Override
    public ReliableMessage getMessageByMessageId(String messageId) {
        return null;
    }

    @Override
    public void deleteMessageByMessageId(String messageId) {

    }

    @Override
    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) {

    }

    @Override
    public Page listPage(Page pageParam, Map<String, Object> paramMap) {
        return null;
    }
}
