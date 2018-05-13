package com.stylefeng.roses.api.message;


import com.stylefeng.roses.api.common.page.PageQuery;
import com.stylefeng.roses.api.common.page.PageResult;
import com.stylefeng.roses.api.message.model.ReliableMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 消息服务的接口
 *
 * @author stylefeng
 * @Date 2018/4/16 21:47
 */
@RequestMapping("/api/messageService")
public interface MessageServiceApi {

    /**
     * 预存储消息
     */
    @RequestMapping(value = "/preSaveMessage", method = RequestMethod.POST)
    ReliableMessage preSaveMessage(@RequestBody ReliableMessage reliableMessage);

    /**
     * 确认并发送消息
     */
    @RequestMapping("/confirmAndSendMessage")
    void confirmAndSendMessage(@RequestParam("messageId") String messageId);

    /**
     * 存储并发送消息
     */
    @RequestMapping("/saveAndSendMessage")
    void saveAndSendMessage(@RequestBody ReliableMessage reliableMessage);

    /**
     * 直接发送消息
     */
    @RequestMapping("/directSendMessage")
    void directSendMessage(@RequestBody ReliableMessage reliableMessage);

    /**
     * 重发消息
     */
    @RequestMapping("/reSendMessage")
    void reSendMessage(@RequestBody ReliableMessage reliableMessage);

    /**
     * 根据messageId重发某条消息
     */
    @RequestMapping("/reSendMessageByMessageId")
    void reSendMessageByMessageId(@RequestParam("messageId") String messageId);

    /**
     * 将消息标记为死亡消息
     */
    @RequestMapping("/setMessageToAreadlyDead")
    void setMessageToAreadlyDead(@RequestParam("messageId") String messageId);

    /**
     * 根据消息ID获取消息
     */
    @RequestMapping("/getMessageByMessageId")
    ReliableMessage getMessageByMessageId(@RequestParam("messageId") String messageId);

    /**
     * 根据消息ID删除消息
     */
    @RequestMapping("/deleteMessageByMessageId")
    void deleteMessageByMessageId(@RequestParam("messageId") String messageId);

    /**
     * 根据业务id删除消息
     */
    @RequestMapping("/deleteMessageByBizId")
    void deleteMessageByBizId(@RequestParam("bizId") Long bizId);

    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    @RequestMapping("/reSendAllDeadMessageByQueueName")
    void reSendAllDeadMessageByQueueName(@RequestParam("queueName") String queueName);

    /**
     * 分页获取待发送超时的数据
     */
    @RequestMapping("/listPagetWaitConfimTimeOutMessages")
    PageResult<ReliableMessage> listPagetWaitConfimTimeOutMessages(@RequestBody PageQuery pageParam);

    /**
     * 分页获取发送中超时的数据
     */
    @RequestMapping("/listPageSendingTimeOutMessages")
    PageResult<ReliableMessage> listPageSendingTimeOutMessages(@RequestBody PageQuery pageParam);

}
