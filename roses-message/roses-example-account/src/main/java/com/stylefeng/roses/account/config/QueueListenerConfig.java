package com.stylefeng.roses.account.config;

import com.stylefeng.roses.account.core.listener.AccountListener;
import com.stylefeng.roses.api.message.enums.MessageQueueEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

/**
 * 消息队列监听配置
 *
 * @author fengshuonan
 * @date 2018-05-06 13:11
 */
@Configuration
public class QueueListenerConfig {

    @Autowired
    private AccountListener accountListener;

    @Bean
    public DefaultMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        DefaultMessageListenerContainer factory = new DefaultMessageListenerContainer();
        factory.setConnectionFactory(connectionFactory);
        factory.setDestinationName(MessageQueueEnum.MAKE_ORDER.name());
        factory.setMessageListener(accountListener);
        return factory;
    }
}
