package com.stylefeng.roses.order.modular.controller;

import com.stylefeng.roses.order.modular.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制器
 *
 * @author fengshuonan
 * @date 2018-05-06 21:24
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 下订单
     */
    @RequestMapping("/place")
    public String place() {
        Long orderId = orderService.makeTestOrder();
        return "order_id = " + orderId;
    }


    /**
     * 完成订单
     */
    @RequestMapping("/finish")
    public String finish(@RequestParam("orderId") String orderId) {
        orderService.finishOrder(orderId);
        return "success";
    }
}
