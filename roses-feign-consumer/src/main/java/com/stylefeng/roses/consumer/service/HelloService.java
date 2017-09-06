package com.stylefeng.roses.consumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("message-service")
public interface HelloService {

    @RequestMapping(value = "/hello")
    String helloConsumer();
}
