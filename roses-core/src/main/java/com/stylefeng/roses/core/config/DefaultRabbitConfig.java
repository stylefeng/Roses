package com.stylefeng.roses.core.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq
 *
 * @author fengshuonan
 * @date 2017-11-18-下午5:20
 */
@Configuration
public class DefaultRabbitConfig {

    private static final String LOG_QUEUE = "logQueue";

    @Bean
    Queue queue() {
        return new Queue(LOG_QUEUE, false);
    }
}
