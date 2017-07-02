package com.stylefeng.roses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者控制器
 *
 * @author fengshuonan
 * @date 2017-07-02 23:44
 */
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String helloConsumer(){
        return restTemplate.getForEntity("http://MESSAGE-SERVICE/hello",String.class).getBody();
    }
}
