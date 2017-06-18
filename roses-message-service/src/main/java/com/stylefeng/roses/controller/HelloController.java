package com.stylefeng.roses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello
 *
 * @author fengshuonan
 * @date 2017-06-18 23:29
 */
@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public String index(){
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        System.out.println(serviceInstance.getHost());
        System.out.println(serviceInstance.getServiceId());
        return "Hello World!";
    }
}
