package com.stylefeng.roses.account.modular.consumer;

import com.stylefeng.roses.api.message.MessageServiceApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 消息服务消费者
 *
 * @author fengshuonan
 * @date 2018-05-05 22:39
 */
@FeignClient("roses-message-service")
public interface MessageServiceConsumer extends MessageServiceApi {
}
