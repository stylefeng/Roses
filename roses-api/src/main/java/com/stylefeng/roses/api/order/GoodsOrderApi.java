package com.stylefeng.roses.api.order;


import com.stylefeng.roses.api.order.model.GoodsOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 商品订单的接口
 *
 * @author stylefeng
 * @Date 2018/4/16 21:47
 */
@RequestMapping("/api/goodsOrder")
public interface GoodsOrderApi {

    /**
     * 根据订单id查询订单记录
     */
    @RequestMapping("/findOrderById")
    GoodsOrder findOrderById(@RequestParam("orderId") Long orderId);

}
