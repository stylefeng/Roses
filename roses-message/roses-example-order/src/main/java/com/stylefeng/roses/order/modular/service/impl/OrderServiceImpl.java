package com.stylefeng.roses.order.modular.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.stylefeng.roses.api.message.enums.MessageQueueEnum;
import com.stylefeng.roses.api.message.model.ReliableMessage;
import com.stylefeng.roses.api.order.model.GoodsOrder;
import com.stylefeng.roses.order.modular.consumer.MessageServiceConsumer;
import com.stylefeng.roses.order.modular.mapper.OrderMapper;
import com.stylefeng.roses.order.modular.service.IOrderService;
import com.xiaoleilu.hutool.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

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

    @Autowired
    private MessageServiceConsumer messageServiceConsumer;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void makeTestOrder() {

        //创建预发送消息
        GoodsOrder goods = createGoods();
        ReliableMessage reliableMessage = createMessage(goods);

        //预发送消息
        messageServiceConsumer.preSaveMessage(reliableMessage);

        //下单
        this.insert(goods);

        //确认消息
        messageServiceConsumer.confirmAndSendMessage(reliableMessage.getMessageId());
    }


    private ReliableMessage createMessage(GoodsOrder goodsOrder) {
        String messageId = IdWorker.getIdStr();
        String messageBody = JSON.toJSONString(goodsOrder);
        String queue = MessageQueueEnum.MAKE_ORDER.name();

        return new ReliableMessage(messageId, messageBody, queue);
    }

    private GoodsOrder createGoods() {
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setGoodsName("商品" + RandomUtil.randomNumbers(4));
        goodsOrder.setCount(Integer.valueOf(RandomUtil.randomNumbers(1)));
        goodsOrder.setCreateTime(new Date());
        goodsOrder.setSum(new BigDecimal(RandomUtil.randomDouble(10.0, 50.0)).setScale(2, RoundingMode.HALF_UP));
        goodsOrder.setId(IdWorker.getId());
        goodsOrder.setUserId(IdWorker.getId());
        return goodsOrder;
    }
}
