package com.stylefeng.roses.message.checker.service;

/**
 * 消息校验重发服务
 *
 * @author fengshuonan
 * @date 2018-05-07 23:03
 */
public interface MessageCheckService {

    /**
     * 处理状态为“待确认”但已超时的消息
     *
     * @author stylefeng
     * @Date 2018/5/7 23:05
     */
    void disposeWaitingConfirmTimeOutMessages();

    /**
     * 处理状态为“发送中”但超时没有被成功消费确认的消息
     *
     * @author stylefeng
     * @Date 2018/5/7 23:05
     */
    void disposeSendingTimeOutMessage();
}
