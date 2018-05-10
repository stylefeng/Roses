package com.stylefeng.roses.message.checker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息检查的配置
 *
 * @author fengshuonan
 * @date 2018-05-10 23:24
 */
@Configuration
public class MessageCheckerConfig {

    /**
     * 消息发送的次数和间隔
     */
    @Bean(name = "sendTimeInterval")
    public Map<Integer, Integer> sendTimeInterval() {
        Map<Integer, Integer> notifyParam = new HashMap<>();
        notifyParam.put(1, 1);
        notifyParam.put(2, 3);
        notifyParam.put(3, 5);
        notifyParam.put(4, 15);
        notifyParam.put(5, 30);
        return notifyParam;
    }

}
