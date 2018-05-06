package com.stylefeng.roses.order.modular.controller;

import com.stylefeng.roses.order.modular.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 *
 * @author fengshuonan
 * @date 2018-05-06 21:24
 */
@RestController
public class TestController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/ttt")
    public String test() {
        orderService.makeTestOrder();
        return "success";
    }

}
