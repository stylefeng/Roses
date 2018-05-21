package com.stylefeng.roses.order.modular.controller;

import com.stylefeng.roses.api.order.model.GoodsOrder;
import com.stylefeng.roses.core.base.request.RequestData;
import com.stylefeng.roses.core.base.response.ResponseData;
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

    /**
     * 测试RequestData
     */
    @RequestMapping("/test")
    public Object test(RequestData requestData) {

        String orderId = requestData.getString("goodsName");
        System.out.println(orderId);

        Integer number = requestData.getInteger("count");
        System.out.println(number);

        GoodsOrder goodsOrder = requestData.parse(GoodsOrder.class);
        System.out.println(goodsOrder);

        return ResponseData.success(goodsOrder);
    }
}
