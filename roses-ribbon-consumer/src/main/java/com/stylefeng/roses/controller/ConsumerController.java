package com.stylefeng.roses.controller;

import com.stylefeng.roses.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

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

    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String helloConsumer() throws ExecutionException, InterruptedException {


        return helloService.helloService();

        //Future<String> stringFuture = helloService.syncService();
        //return stringFuture.get();

        //Observable<String> stringFuture = helloService.hello();
        //return stringFuture.toSingle().toBlocking().value();

    }
}
