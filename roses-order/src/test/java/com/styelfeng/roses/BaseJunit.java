package com.styelfeng.roses;

import com.stylefeng.roses.order.RosesOrderApplication;
import com.stylefeng.roses.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * 基础测试类
 *
 * @author stylefeng
 * @Date 2017/5/21 16:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RosesOrderApplication.class)
@WebAppConfiguration
public class BaseJunit {

    @Autowired
    OrderService orderService;

    @Test
    public void testOrder() throws Exception {
        orderService.placeOrder(null);

    }
}
