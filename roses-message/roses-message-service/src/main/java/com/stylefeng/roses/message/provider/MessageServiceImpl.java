package com.stylefeng.roses.message.provider;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.roses.api.message.MessageServiceApi;
import com.stylefeng.roses.api.message.model.BizMessage;

import java.util.Map;

/**
 * 消息服务提供接口的实现
 *
 * @author fengshuonan
 * @date 2018-04-16 22:30
 */
public class MessageServiceImpl implements MessageServiceApi {
    @Override
    public int saveMessageWaitingConfirm(BizMessage bizMessage) {
        return 0;
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
