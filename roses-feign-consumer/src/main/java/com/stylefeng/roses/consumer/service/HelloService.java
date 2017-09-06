package com.stylefeng.roses.consumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("message-service")
public interface HelloService extends com.stylefeng.roses.facade.hello.HelloService {

}
