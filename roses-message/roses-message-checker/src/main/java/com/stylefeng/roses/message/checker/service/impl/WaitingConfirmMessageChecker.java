package com.stylefeng.roses.message.checker.service.impl;

import com.stylefeng.roses.api.common.page.PageQuery;
import com.stylefeng.roses.api.common.page.PageResult;
import com.stylefeng.roses.api.message.model.ReliableMessage;
import com.stylefeng.roses.api.order.model.GoodsOrder;
import com.stylefeng.roses.core.util.LogUtil;
import com.stylefeng.roses.message.checker.consumer.GoodsOrderConsumer;
import com.stylefeng.roses.message.checker.consumer.MessageServiceConsumer;
import com.stylefeng.roses.message.checker.service.AbstractMessageChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 处理状态为“待确认”但已超时的消息
 *
 * @author fengshuonan
 * @date 2018-05-08 23:07
 */
@Service
public class WaitingConfirmMessageChecker extends AbstractMessageChecker {

    @Autowired
    private MessageServiceConsumer messageServiceConsumer;

    @Autowired
    private GoodsOrderConsumer goodsOrderConsumer;

    @Override
    protected void processWaitingConfirmTimeOutMessages(Map<String, ReliableMessage> messages) {
        for (Map.Entry<String, ReliableMessage> entry : messages.entrySet()) {
            ReliableMessage message = entry.getValue();
            try {
                Long orderId = message.getBizUniqueId();
                GoodsOrder order = goodsOrderConsumer.findOrderById(orderId);

                if (order != null) {
                    messageServiceConsumer.confirmAndSendMessage(message.getMessageId());
                } else {

                    //TODO 订单加状态，根据状态判断
                    messageServiceConsumer.deleteMessageByMessageId(message.getMessageId());
                }
            } catch (Exception e) {
                LogUtil.error("处理待确认消息异常！messageId=" + message.getMessageId());
            }
        }
    }

    @Override
    protected PageResult<ReliableMessage> getPageResult(PageQuery pageQuery) {
        return messageServiceConsumer.listPagetWaitConfimTimeOutMessages(pageQuery);
    }

}
