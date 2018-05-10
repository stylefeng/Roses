package com.stylefeng.roses.message.config.properteis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 消息服务的配置
 *
 * @author fengshuonan
 * @date 2018-05-10 22:55
 */
@Component
@ConfigurationProperties(prefix = "roses.message")
public class MessageProperties {

    /**
     * 消息检查的时间段
     */
    private Integer checkInterval;

    public Integer getCheckInterval() {
        return checkInterval;
    }

    public void setCheckInterval(Integer checkInterval) {
        this.checkInterval = checkInterval;
    }
}
