package com.stylefeng.roses.order.remote;

import com.stylefeng.roses.facade.api.MessageServiceApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 消息服务
 *
 * @author fengshuonan
 * @date 2017-10-20-下午10:23
 */
@FeignClient("message-service")
public interface MessageService extends MessageServiceApi {
}
