package com.stylefeng.roses.consumer.controller;

import com.stylefeng.roses.consumer.service.HelloService;
import com.stylefeng.roses.consumer.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消费者控制器
 *
 * @author fengshuonan
 * @date 2017-07-02 23:44
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/fegin-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        return helloService.helloConsumer();

    }

    @RequestMapping(value = "/fegin-consumer2", method = RequestMethod.GET)
    public String helloConsumer2() {
        System.out.println(helloService.helloConsumer());
        System.out.println(helloService.helloConsumer(new User("name", 12)));
        System.out.println(helloService.helloConsumer("abc"));
        System.out.println(helloService.helloConsumer("aaa", 222));
        return "success";
    }
}
