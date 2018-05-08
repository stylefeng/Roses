package com.stylefeng.roses.order.modular.provider;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.roses.api.order.GoodsOrderApi;
import com.stylefeng.roses.api.order.model.GoodsOrder;
import com.stylefeng.roses.order.modular.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 消息服务的接口
 *
 * @author stylefeng
 * @Date 2018/4/16 21:47
 */
@RestController
public class GoodsOrderProvider implements GoodsOrderApi {

    @Autowired
    private IOrderService orderService;

    @Override
    public GoodsOrder findOrderById(@RequestParam("orderId") Long orderId) {

        List<GoodsOrder> orders = orderService.selectList(
                new EntityWrapper<GoodsOrder>().eq("order_id", orderId));

        if (orders != null && !orders.isEmpty()) {
            return orders.get(0);
        } else {
            return null;
        }

    }
}