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
    void confirmAndSendMessage(String messageId);

    /**
     * 存储并发送消息
     */
    @RequestMapping("/saveAndSendMessage")
    int saveAndSendMessage(ReliableMessage reliableMessage);

    /**
     * 直接发送消息
     */
    @RequestMapping("/directSendMessage")
    void directSendMessage(ReliableMessage reliableMessage);

    /**
     * 重发消息
     */
    @RequestMapping("/reSendMessage")
    void reSendMessage(ReliableMessage reliableMessage);

    /**
     * 根据messageId重发某条消息
     */
    @RequestMapping("/reSendMessageByMessageId")
    void reSendMessageByMessageId(String messageId);

    /**
     * 将消息标记为死亡消息
     */
    @RequestMapping("/setMessageToAreadlyDead")
    void setMessageToAreadlyDead(String messageId);

    /**
     * 根据消息ID获取消息
     */
    @RequestMapping("/getMessageByMessageId")
    ReliableMessage getMessageByMessageId(String messageId);

    /**
     * 根据消息ID删除消息
     */
    @RequestMapping("/deleteMessageByMessageId")
    void deleteMessageByMessageId(String messageId);

    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    @RequestMapping("/reSendAllDeadMessageByQueueName")
    void reSendAllDeadMessageByQueueName(@RequestParam("queueName") String queueName, @RequestParam("batchSize") int batchSize);

    /**
     * 获取分页数据
     */
    @RequestMapping("/listPage")
    PageResult listPage(PageQuery pageParam);

}
