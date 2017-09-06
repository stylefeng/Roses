package com.stylefeng.roses.consumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("message-service")
public interface HelloService {

    @RequestMapping(value = "/hello")
    String helloConsumer();

    @RequestMapping(value = "/hello1")
    String helloConsumer(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2")
    User helloConsumer(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello3")
    String helloConsumer(@RequestBody User user);
}
