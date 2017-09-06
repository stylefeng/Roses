package com.stylefeng.roses.facade.hello;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * hello service
 *
 * @author fengshuonan
 * @date 2017-09-06 17:35
 */
@RequestMapping("/refactor")
public interface HelloService {

    @RequestMapping("/hello4")
    String hello4(@RequestParam("name") String name);

    @RequestMapping("/hello5")
    User hello5(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping("/hello6")
    String hello6(@RequestBody User user);
}
