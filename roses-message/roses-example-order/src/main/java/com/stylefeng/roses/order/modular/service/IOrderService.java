package com.stylefeng.roses.order.modular.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.roses.api.order.model.GoodsOrder;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-05
 */
public interface IOrderService extends IService<GoodsOrder> {

    /**
     * <pre>
     * 测试下单业务
     * </pre>
     *
     * @author stylefeng
     * @Date 2018/5/6 12:10
     */
    Long makeTestOrder();

    /**
     * <pre>
     * 完成订单
     *
     * 说明：
     *  本业务为分布式事务解决方案之可靠消息最终一致性方案的实例
     * </pre>
     *
     * @author stylefeng
     * @Date 2018/5/6 12:10
     */
    void finishOrder(String orderId);
}
