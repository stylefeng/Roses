package com.stylefeng.roses.account.config;

import com.stylefeng.roses.account.core.listener.AccountListener;
import com.stylefeng.roses.api.message.enums.MessageQueueEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;

/**
 * 消息队列监听配置
 *
 * @author fengshuonan
 * @date 2018-05-06 13:11
 */
@Configuration
public class QueueListenerConfig implements JmsListenerConfigurer {

    @Autowired
    private AccountListener accountListener;

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
        SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
        endpoint.setId("accountJmsEndpoint");
        endpoint.setDestination(MessageQueueEnum.MAKE_ORDER.name());
        endpoint.setMessageListener(accountListener);
        registrar.registerEndpoint(endpoint);
    }
}
