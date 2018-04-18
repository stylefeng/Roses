package com.stylefeng.roses.message.provider;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.roses.api.common.enums.YseOrNotEnum;
import com.stylefeng.roses.api.message.MessageServiceApi;
import com.stylefeng.roses.api.message.enums.MessageStatusEnum;
import com.stylefeng.roses.api.message.exception.MessageExceptionEnum;
import com.stylefeng.roses.api.message.model.BizMessage;
import com.stylefeng.roses.core.context.UserContext;
import com.stylefeng.roses.core.exception.CoreExceptionEnum;
import com.stylefeng.roses.core.exception.ServiceException;
import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.message.service.IBizMessageService;
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
    private IBizMessageService bizMessageService;

    @Override
    public void preStoreMessage(BizMessage bizMessage) {
        if (bizMessage == null) {
            throw new ServiceException(CoreExceptionEnum.REQUEST_NULL);
        }
        if (ToolUtil.isEmpty(bizMessage.getConsumerQueue())) {
            throw new ServiceException(MessageExceptionEnum.QUEUE_CANT_EMPTY);
        }
        bizMessage.setCreateBy(UserContext.me().getUser().getId().toString());
        bizMessage.setStatus(MessageStatusEnum.WAIT_VERIFY.name());
        bizMessage.setAreadlyDead(YseOrNotEnum.N.name());
        bizMessage.setMessageSendTimes(0);
        bizMessageService.insert(bizMessage);
    }

    @Override
    public void confirmAndSendMessage(String messageId) {

    }

    @Override
    public int saveAndSendMessage(BizMessage bizMessage) {
        return 0;
    }

    @Override
    public void directSendMessage(BizMessage bizMessage) {

    }

    @Override
    public void reSendMessage(BizMessage bizMessage) {

    }

    @Override
    public void reSendMessageByMessageId(String messageId) {

    }

    @Override
    public void setMessageToAreadlyDead(String messageId) {

    }

    @Override
    public BizMessage getMessageByMessageId(String messageId) {
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
