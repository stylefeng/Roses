package com.stylefeng.roses.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * hello service
 * <p>
 * fallback方法的参数要和被调用处一致
 * batchMethod参数要为list返回的也要为list
 *
 * @author fengshuonan
 * @date 2017-08-28 10:07
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 请求合并
     */
    @HystrixCommand
    public List<String> getUsersByIds(List<String> ids) {
        ArrayList<String> objects = new ArrayList<>();
        objects.add("123");
        objects.add("456");
        return objects;
    }

    /**
     * 请求合并
     */
    @HystrixCollapser(batchMethod = "getUsersByIds", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")})
    public String getUserById(String id) {
        return null;
    }

    /**
     * 缓存用法
     */
    @CacheResult(cacheKeyMethod = "getUserNameByIdCacheKey")
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String getUserNameById(String id) {
        return restTemplate.getForEntity("http://MESSAGE-SERVICE/hello", String.class).getBody();
    }

    public String getUserNameByIdCacheKey(String id) {
        return "THE_KEY_" + id;
    }

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
    public String helloFallback(String s, Throwable e) {
        System.out.println(e);
        return "error";
    }
}
