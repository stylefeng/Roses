package com.stylefeng.roses.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * hello service
 *
 * @author fengshuonan
 * @date 2017-08-28 10:07
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService() {
        return restTemplate.getForEntity("http://MESSAGE-SERVICE/hello", String.class).getBody();
    }

    public String helloFallback() {
        return "error";
    }
}
