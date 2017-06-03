package com.stylefeng.roses.facade.api;

import com.stylefeng.roses.facade.entity.ServiceMessage;
import com.stylefeng.roses.facade.exception.MsgServiceException;

/**
 * 消息服务子系统开放的接口
 *
 * @author fengshuonan
 * @date 2017-05-29 22:43
 */
public interface MessageServiceApi {

    /**
     * 预存储消息
     *
     * @author stylefeng
     * @Date 2017/6/2 22:32
     */
    void saveMessageWaitingConfirm(ServiceMessage serviceMessage) throws MsgServiceException;

    /**
     * 确认并发送消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 13:24
     */
    void confirmAndSendMessage(String messageId) throws MsgServiceException;

    /**
     * 保存并发送消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 13:25
     */
    void saveAndSendMessage(ServiceMessage serviceMessage) throws MsgServiceException;

    /**
     * 直接发送消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 13:26
     */
    void directSendMessage(ServiceMessage serviceMessage) throws MsgServiceException;

    /**
     * 重新发送消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 14:59
     */
    void reSendMessage(ServiceMessage serviceMessage) throws MsgServiceException;



}
