package com.stylefeng.roses.account.core.listener;

import com.alibaba.fastjson.JSON;
import com.stylefeng.roses.account.modular.service.IFlowRecordService;
import com.stylefeng.roses.api.common.exception.ServiceException;
import com.stylefeng.roses.api.message.exception.MessageExceptionEnum;
import com.stylefeng.roses.api.order.GoodsFlowParam;
import com.stylefeng.roses.core.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class AccountListener implements MessageListener {

    @Autowired
    private IFlowRecordService flowRecordService;

    @Override
    public void onMessage(Message message) {

        if (message instanceof TextMessage) {
            try {
                String messageBody = ((TextMessage) message).getText();

                if (ToolUtil.isEmpty(messageBody)) {
                    throw new ServiceException(MessageExceptionEnum.MESSAGE_BODY_CANT_EMPTY);
                }

                GoodsFlowParam goodsFlowParam = JSON.parseObject(messageBody, GoodsFlowParam.class);
                flowRecordService.recordFlow(goodsFlowParam);

                //message.acknowledge();

            } catch (JMSException ex) {
                throw new ServiceException(MessageExceptionEnum.MESSAGE_QUEUE_ERROR);
            }

        } else {
            throw new ServiceException(MessageExceptionEnum.MESSAGE_TYPE_ERROR);
        }
    }

}