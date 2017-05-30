package com.stylefeng.roses.queue;

import com.stylefeng.roses.base.BaseJunit;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Destination;

/**
 * 测试mq
 *
 * @author fengshuonan
 * @date 2017-05-30 21:46
 */
public class MqTest extends BaseJunit {

    @Autowired
    private Producer producer;

    @Test
    public void contextLoads() throws InterruptedException {
        Destination destination = new ActiveMQQueue("stylefeng.queue");

        for (int i = 0; i < 100; i++) {
            producer.sendMessage(destination, "StyleFeng");
        }
    }
}
