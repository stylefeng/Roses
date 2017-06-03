package com.stylefeng.roses.service;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.stylefeng.roses.base.BaseJunit;
import com.stylefeng.roses.core.enums.IsOrNot;
import com.stylefeng.roses.facade.api.MessageServiceApi;
import com.stylefeng.roses.facade.entity.ServiceMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消息子系统服务测试
 *
 * @author fengshuonan
 * @date 2017-06-02 23:55
 */
public class ServiceMessageTest extends BaseJunit {

    @Autowired
    MessageServiceApi messageServiceApi;

    @Test
    public void savePreMessageTest() {
        ServiceMessage serviceMessage = new ServiceMessage();
        serviceMessage.setMessageId(IdWorker.get32UUID());
        serviceMessage.setMessageSendTimes(0);
        serviceMessage.setConsumerQueue("orderQueue");
        serviceMessage.setMessageBody("savePreMessageTest");
        serviceMessage.setMessageDataType("json");
        serviceMessage.setAreadlyDead(IsOrNot.NO.name());
        messageServiceApi.saveMessageWaitingConfirm(serviceMessage);
    }

    @Test
    public void confirmAndSendMessageTest() {
        messageServiceApi.confirmAndSendMessage("1befefb6d5e34c33a44b2b8f8a5a3765");
    }

    @Test
    public void saveAndSendMessageTest() {
        ServiceMessage serviceMessage = new ServiceMessage();
        serviceMessage.setMessageId(IdWorker.get32UUID());
        serviceMessage.setMessageSendTimes(0);
        serviceMessage.setConsumerQueue("orderQueue");
        serviceMessage.setMessageBody("saveAndSendMessageTest");
        serviceMessage.setMessageDataType("json");
        serviceMessage.setAreadlyDead(IsOrNot.NO.name());
        messageServiceApi.saveAndSendMessage(serviceMessage);
    }

    @Test
    public void directSendMessageTest() {
        ServiceMessage serviceMessage = new ServiceMessage();
        serviceMessage.setMessageId(IdWorker.get32UUID());
        serviceMessage.setMessageSendTimes(0);
        serviceMessage.setConsumerQueue("orderQueue");
        serviceMessage.setMessageBody("directSendMessageTest");
        serviceMessage.setMessageDataType("json");
        serviceMessage.setAreadlyDead(IsOrNot.NO.name());
        messageServiceApi.directSendMessage(serviceMessage);
    }

    @Test
    public void reSendMessageTest() {
        ServiceMessage serviceMessage = new ServiceMessage();
        serviceMessage.setMessageId("4577eed1d6d140eaa8432b6962b9c0c2");
        serviceMessage.setMessageSendTimes(0);
        serviceMessage.setConsumerQueue("orderQueue");
        serviceMessage.setMessageBody("directSendMessageTest");
        serviceMessage.setMessageDataType("json");
        messageServiceApi.reSendMessage(serviceMessage);
    }

    @Test
    public void reSendMessageByMessageIdTest() {
        messageServiceApi.reSendMessageByMessageId("4577eed1d6d140eaa8432b6962b9c0c2");
    }

    @Test
    public void deleteMessageByMessageIdTest() {
        messageServiceApi.deleteMessageByMessageId("4577eed1d6d140eaa8432b6962b9c0c2");
    }

    @Test
    public void reSendAllDeadMessageByQueueNameTest() {
        messageServiceApi.reSendAllDeadMessageByQueueName("orderQueue");
    }

}
