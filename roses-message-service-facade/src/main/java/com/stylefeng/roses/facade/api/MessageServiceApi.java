package com.stylefeng.roses.facade.api;

import com.stylefeng.roses.facade.entity.ServiceMessage;
import com.stylefeng.roses.facade.exception.MsgServiceException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 消息服务子系统开放的接口
 *
 * @author fengshuonan
 * @date 2017-05-29 22:43
 */
@RequestMapping("/messageService")
public interface MessageServiceApi {

    /**
     * 预存储消息
     *
     * @author stylefeng
     * @Date 2017/6/2 22:32
     */
    @RequestMapping("/saveMessageWaitingConfirm")
    void saveMessageWaitingConfirm(@RequestBody ServiceMessage serviceMessage) throws MsgServiceException;

    /**
     * 确认并发送消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 13:24
     */
    @RequestMapping("/confirmAndSendMessage/{messageId}")
    void confirmAndSendMessage(@PathVariable("messageId") String messageId) throws MsgServiceException;

    /**
     * 保存并发送消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 13:25
     */
    @RequestMapping("/confirmAndSendMessage")
    void saveAndSendMessage(@RequestBody ServiceMessage serviceMessage) throws MsgServiceException;

    /**
     * 直接发送消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 13:26
     */
    @RequestMapping("/directSendMessage")
    void directSendMessage(@RequestBody ServiceMessage serviceMessage) throws MsgServiceException;

    /**
     * 重新发送消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 14:59
     */
    @RequestMapping("/reSendMessage")
    void reSendMessage(@RequestBody ServiceMessage serviceMessage) throws MsgServiceException;

    /**
     * 根据消息id重新发送消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 15:56
     */
    @RequestMapping("/reSendMessageByMessageId/{messageId}")
    void reSendMessageByMessageId(@PathVariable("messageId") String messageId) throws MsgServiceException;

    /**
     * 通过消息id删除消息
     *
     * @author fengshuonan
     * @Date 2017/6/3 16:25
     */
    @RequestMapping("/deleteMessageByMessageId/{messageId}")
    void deleteMessageByMessageId(@PathVariable("messageId") String messageId) throws MsgServiceException;

    /**
     * 重新发送所有已死亡的消息,通过队列名称
     *
     * @author fengshuonan
     * @Date 2017/6/3 16:38
     */
    @RequestMapping("/reSendAllDeadMessageByQueueName/{queue}")
    void reSendAllDeadMessageByQueueName(@PathVariable("queue") String queue);

}
