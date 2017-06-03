package com.stylefeng.roses.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * roses项目配置
 *
 * @author stylefeng
 * @Date 2017年6月3日 16:06:02
 */
@Component
@ConfigurationProperties(prefix = RosesProperties.PREFIX)
public class RosesProperties {

    public static final String PREFIX = "roses";

    private Integer messageMaxSendTimes = 5;

    public static String getPREFIX() {
        return PREFIX;
    }

    public Integer getMessageMaxSendTimes() {
        return messageMaxSendTimes;
    }

    public void setMessageMaxSendTimes(Integer messageMaxSendTimes) {
        this.messageMaxSendTimes = messageMaxSendTimes;
    }
}
