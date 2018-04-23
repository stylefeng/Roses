package com.stylefeng.roses.message.config;

import com.stylefeng.roses.message.core.activemq.MessageSender;
import com.stylefeng.roses.message.core.activemq.impl.ActiveMqMessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息发送者配置
 *
 * @author fengshuonan
 * @date 2018-04-22 22:06
 */
@Configuration
public class MessageSenderConfig {

    @Bean
    public MessageSender messageSender() {
        return new ActiveMqMessageSender();
    }
}
