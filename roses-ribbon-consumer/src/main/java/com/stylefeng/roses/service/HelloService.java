package com.stylefeng.roses.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    /**
     * 简单用法
     */
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService() {
        throw new RuntimeException("调用失败!");
        //return restTemplate.getForEntity("http://MESSAGE-SERVICE/hello", String.class).getBody();
    }

    /**
     * 异步执行
     */
    @HystrixCommand(fallbackMethod = "helloFallback")
    public Future<String> syncService() {
        return new AsyncResult<String>("eeeeeeeeeee") {
            @Override
            public String get() throws ExecutionException {
                return restTemplate.getForEntity("http://MESSAGE-SERVICE/hello", String.class).getBody();
            }
        };
    }

    /**
     * 发射多次observable
     */
    @HystrixCommand(fallbackMethod = "helloFallback", observableExecutionMode = ObservableExecutionMode.EAGER)
//eager Hot observable  LAZY/Cold observable
    public Observable<String> hello() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        String body = restTemplate.getForEntity("http://MESSAGE-SERVICE/hello", String.class).getBody();
                        subscriber.onNext(body);
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * 加上Throwable参数可以获取具体抛出的异常
     */
    public String helloFallback(Throwable e) {
        System.out.println(e);
        return "error";
    }
}
