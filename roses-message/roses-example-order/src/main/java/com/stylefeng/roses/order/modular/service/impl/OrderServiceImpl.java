package com.stylefeng.roses.order.modular.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.roses.order.modular.entity.GoodsOrder;
import com.stylefeng.roses.order.modular.mapper.OrderMapper;
import com.stylefeng.roses.order.modular.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-05
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, GoodsOrder> implements IOrderService {

}
