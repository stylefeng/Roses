package com.stylefeng.roses.service;


import com.stylefeng.roses.core.utils.ToolUtil;
import com.stylefeng.roses.facade.api.MessageServiceApi;
import com.stylefeng.roses.facade.entity.ServiceMessage;
import com.stylefeng.roses.facade.enums.MsgServiceExceptionEnum;
import com.stylefeng.roses.facade.exception.MsgServiceException;
import com.stylefeng.roses.factory.MessageFactory;
import com.stylefeng.roses.persistence.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 消息服务子系统接口的实现
 *
 * @author fengshuonan
 * @date 2017-05-29 22:44
 */
@Service
@Transactional
public class MessageServiceApiImpl implements MessageServiceApi {

    @Override
    public boolean saveMessageWaitingConfirm(ServiceMessage serviceMessage) {
        if(null == serviceMessage){
            throw new MsgServiceException(MsgServiceExceptionEnum.REQUEST_NULL);
        }
        if(ToolUtil.isEmpty(serviceMessage.getConsumerQueue())){
            throw new MsgServiceException(MsgServiceExceptionEnum.QUEUE_CANT_BE_NULL);
        }
        Message message = MessageFactory.createPreMessage(serviceMessage);
        return message.insert();
    }
}
