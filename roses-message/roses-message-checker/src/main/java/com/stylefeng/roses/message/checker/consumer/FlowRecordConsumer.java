package com.stylefeng.roses.message.checker.consumer;


import com.stylefeng.roses.api.account.FlowRecordApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 消息服务的接口
 *
 * @author stylefeng
 * @Date 2018/4/16 21:47
 */
@FeignClient("roses-account")
public interface FlowRecordConsumer extends FlowRecordApi {

}