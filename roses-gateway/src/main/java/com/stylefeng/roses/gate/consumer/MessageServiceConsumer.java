package com.stylefeng.roses.gate.consumer;

import com.stylefeng.roses.api.message.MessageServiceApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 消息服务消费者
 *
 * @author fengshuonan
 * @date 2017-11-09-下午7:52
 */
@FeignClient(value = "roses-message-service")
public interface MessageServiceConsumer extends MessageServiceApi {

}
