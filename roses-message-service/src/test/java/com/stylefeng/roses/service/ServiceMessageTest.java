package com.stylefeng.roses.service;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.stylefeng.roses.base.BaseJunit;
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
public class ServiceMessageTest extends BaseJunit{

    @Autowired
    MessageServiceApi messageServiceApi;

    @Test
    public void savePreMessageTest(){
        ServiceMessage serviceMessage = new ServiceMessage();
        serviceMessage.setMessageId(IdWorker.get32UUID());
        serviceMessage.setMessageSendTimes(0);
        serviceMessage.setConsumerQueue("stylefeng");
        serviceMessage.setMessageBody("dsfdsfdsafdsafdsfd");
        serviceMessage.setMessageDataType("json");
        messageServiceApi.saveMessageWaitingConfirm(serviceMessage);
    }
}
