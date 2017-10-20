package com.stylefeng.roses.order.service;

import com.stylefeng.roses.order.persistence.model.UserOrder;
import com.stylefeng.roses.order.remote.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单服务
 *
 * @author fengshuonan
 * @date 2017-10-20-下午10:18
 */
@Service
public class OrderService {

    @Autowired
    MessageService messageService;

    public void placeOrder(UserOrder userOrder){

    }
}
