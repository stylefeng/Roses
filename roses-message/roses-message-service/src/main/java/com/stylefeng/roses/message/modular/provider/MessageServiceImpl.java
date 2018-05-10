package com.stylefeng.roses.message.modular.provider;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.roses.api.common.enums.YseOrNotEnum;
import com.stylefeng.roses.api.common.exception.CoreExceptionEnum;
import com.stylefeng.roses.api.common.exception.ServiceException;
import com.stylefeng.roses.api.common.page.PageQuery;
import com.stylefeng.roses.api.common.page.PageResult;
import com.stylefeng.roses.api.message.MessageServiceApi;
import com.stylefeng.roses.api.message.enums.MessageStatusEnum;
import com.stylefeng.roses.api.message.exception.MessageExceptionEnum;
import com.stylefeng.roses.api.message.model.ReliableMessage;
import com.stylefeng.roses.core.page.PageFactory;
import com.stylefeng.roses.core.util.LogUtil;
import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.message.config.properteis.MessageProperties;
import com.stylefeng.roses.message.core.activemq.MessageSender;
import com.stylefeng.roses.message.modular.service.IReliableMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.stylefeng.roses.api.message.exception.MessageExceptionEnum.CANT_FIND_MESSAGE;
import static com.stylefeng.roses.api.message.exception.MessageExceptionEnum.MESSAGE_NUMBER_WRONG;

/**
 * 消息服务提供接口的实现
 *
 * @author fengshuonan
 * @date 2018-04-16 22:30
 */
@RestController
public class MessageServiceImpl implements MessageServiceApi {

    @Autowired
    private IReliableMessageService reliableMessageService;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private MessageProperties messageProperties;

    @Override
    public ReliableMessage preSaveMessage(@RequestBody ReliableMessage reliableMessage) {

        //检查消息数据的完整性
        this.checkEmptyMessage(reliableMessage);

        //设置状态为待确认
        reliableMessage.setStatus(MessageStatusEnum.WAIT_VERIFY.name());

        //标记未死亡
        reliableMessage.setAreadlyDead(YseOrNotEnum.N.name());
        reliableMessage.setMessageSendTimes(0);
        reliableMessage.setUpdateTime(new Date());
        reliableMessageService.insert(reliableMessage);
        return reliableMessage;
    }

    @Override
    public void confirmAndSendMessage(@RequestParam("messageId") String messageId) {
        EntityWrapper<ReliableMessage> wrapper = new EntityWrapper<>();
        wrapper.eq("message_id", messageId);
        ReliableMessage reliableMessage = this.reliableMessageService.selectOne(wrapper);

        if (reliableMessage == null) {
            throw new ServiceException(MessageExceptionEnum.CANT_FIND_MESSAGE);
        }

        reliableMessage.setStatus(MessageStatusEnum.SENDING.name());
        reliableMessage.setUpdateTime(new Date());
        reliableMessageService.updateById(reliableMessage);

        //发送消息
        messageSender.sendMessage(reliableMessage);
    }

    @Override
    public void saveAndSendMessage(@RequestBody ReliableMessage reliableMessage) {

        //检查消息数据的完整性
        this.checkEmptyMessage(reliableMessage);

        reliableMessage.setStatus(MessageStatusEnum.SENDING.name());
        reliableMessage.setAreadlyDead(YseOrNotEnum.N.name());
        reliableMessage.setMessageSendTimes(0);
        reliableMessage.setUpdateTime(new Date());
        reliableMessageService.insert(reliableMessage);

        //发送消息
        messageSender.sendMessage(reliableMessage);
    }

    @Override
    public void directSendMessage(@RequestBody ReliableMessage reliableMessage) {

        //检查消息数据的完整性
        this.checkEmptyMessage(reliableMessage);

        //发送消息
        messageSender.sendMessage(reliableMessage);
    }

    @Override
    public void reSendMessage(@RequestBody ReliableMessage reliableMessage) {

        //检查消息数据的完整性
        this.checkEmptyMessage(reliableMessage);

        //更新消息发送次数
        reliableMessage.setMessageSendTimes(reliableMessage.getMessageSendTimes() + 1);
        reliableMessage.setUpdateTime(new Date());
        reliableMessageService.updateById(reliableMessage);

        //发送消息
        messageSender.sendMessage(reliableMessage);
    }

    @Override
    public void reSendMessageByMessageId(@RequestParam("messageId") String messageId) {

        if (ToolUtil.isEmpty(messageId)) {
            throw new ServiceException(CoreExceptionEnum.REQUEST_NULL);
        }

        ReliableMessage reliableMessage = getMessageByMessageId(messageId);
        reliableMessage.setMessageSendTimes(reliableMessage.getMessageSendTimes() + 1);
        reliableMessage.setUpdateTime(new Date());
        reliableMessageService.updateById(reliableMessage);

        //发送消息
        messageSender.sendMessage(reliableMessage);
    }

    @Override
    public void setMessageToAreadlyDead(@RequestParam("messageId") String messageId) {

        if (ToolUtil.isEmpty(messageId)) {
            throw new ServiceException(CoreExceptionEnum.REQUEST_NULL);
        }

        ReliableMessage reliableMessage = this.getMessageByMessageId(messageId);
        reliableMessage.setAreadlyDead(YseOrNotEnum.Y.name());
        reliableMessage.setUpdateTime(new Date());

        //发送消息
        messageSender.sendMessage(reliableMessage);
    }

    @Override
    public ReliableMessage getMessageByMessageId(@RequestParam("messageId") String messageId) {

        if (ToolUtil.isEmpty(messageId)) {
            throw new ServiceException(CoreExceptionEnum.REQUEST_NULL);
        }

        ReliableMessage condition = new ReliableMessage();
        condition.setMessageId(messageId);
        EntityWrapper<ReliableMessage> wrapper = new EntityWrapper<>(condition);
        List<ReliableMessage> reliableMessages = this.reliableMessageService.selectList(wrapper);
        if (reliableMessages == null || reliableMessages.size() == 0) {
            throw new ServiceException(CANT_FIND_MESSAGE);
        } else {
            if (reliableMessages.size() > 1) {
                LogUtil.error("消息记录出现错误数据！消息id：" + messageId);
                throw new ServiceException(MESSAGE_NUMBER_WRONG);
            } else {
                return reliableMessages.get(0);
            }
        }
    }

    @Override
    public void deleteMessageByMessageId(@RequestParam("messageId") String messageId) {

        if (ToolUtil.isEmpty(messageId)) {
            throw new ServiceException(CoreExceptionEnum.REQUEST_NULL);
        }

        ReliableMessage condition = new ReliableMessage();
        condition.setMessageId(messageId);
        EntityWrapper<ReliableMessage> wrapper = new EntityWrapper<>(condition);
        this.reliableMessageService.delete(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reSendAllDeadMessageByQueueName(@RequestParam("queueName") String queueName) {

        //默认分页大小为1000
        Integer pageSize = 1000;
        Integer pageNo = 1;

        //存放查询到的所有死亡消息
        Map<String, ReliableMessage> resultMap = new HashMap<>();

        //循环查询所有结构（分页）
        Page<ReliableMessage> pageResult = this.reliableMessageService.selectPage(
                new Page<>(pageNo, pageSize, "create_time", true));
        if (pageResult == null) {
            return;
        }

        List<ReliableMessage> records = pageResult.getRecords();
        if (records == null || records.isEmpty()) {
            return;
        }

        //把结果放入集合
        for (ReliableMessage record : records) {
            resultMap.put(record.getMessageId(), record);
        }

        //循环查出剩下的还有多少,并且都放入集合
        long pages = pageResult.getPages();
        for (pageNo = 2; pageNo <= pages; pageNo++) {
            Page<ReliableMessage> secondPageResult = this.reliableMessageService.selectPage(
                    new Page<>(pageNo, pageSize, "create_time", true));
            if (secondPageResult == null) {
                break;
            }

            List<ReliableMessage> secondRecords = secondPageResult.getRecords();
            if (secondRecords == null || secondRecords.isEmpty()) {
                break;
            }

            for (ReliableMessage record : records) {
                resultMap.put(record.getMessageId(), record);
            }
        }

        //重新发送死亡消息
        for (ReliableMessage reliableMessage : resultMap.values()) {
            reliableMessage.setUpdateTime(new Date());
            reliableMessage.setMessageSendTimes(reliableMessage.getMessageSendTimes() + 1);
            this.reliableMessageService.updateById(reliableMessage);

            this.messageSender.sendMessage(reliableMessage);
        }
    }

    @Override
    public PageResult<ReliableMessage> listPagetWaitConfimTimeOutMessages(@RequestBody PageQuery pageParam) {
        Page<ReliableMessage> page = new PageFactory<ReliableMessage>().createPage(pageParam);
        EntityWrapper<ReliableMessage> wrapper = new EntityWrapper<>();
        wrapper.lt("create_time", ToolUtil.getCreateTimeBefore(messageProperties.getCheckInterval()))
                .and()
                .eq("status", MessageStatusEnum.WAIT_VERIFY.name());
        Page<ReliableMessage> reliableMessagePage = this.reliableMessageService.selectPage(page, wrapper);
        if (page != null) {
            return new PageResult<>(reliableMessagePage);
        } else {
            return new PageResult<>();
        }
    }

    @Override
    public PageResult<ReliableMessage> listPageSendingTimeOutMessages(@RequestBody PageQuery pageParam) {
        Page<ReliableMessage> page = new PageFactory<ReliableMessage>().createPage(pageParam);
        EntityWrapper<ReliableMessage> wrapper = new EntityWrapper<>();
        wrapper.lt("create_time", ToolUtil.getCreateTimeBefore(messageProperties.getCheckInterval()))
                .and()
                .eq("status", MessageStatusEnum.WAIT_VERIFY.name())
                .and()
                .eq("already_dead", YseOrNotEnum.N.name());
        Page<ReliableMessage> reliableMessagePage = this.reliableMessageService.selectPage(page, wrapper);
        if (page != null) {
            return new PageResult<>(reliableMessagePage);
        } else {
            return new PageResult<>();
        }
    }

    /**
     * 检查消息参数是否为空
     *
     * @author stylefeng
     * @Date 2018/4/21 23:14
     */
    private void checkEmptyMessage(ReliableMessage reliableMessage) {
        if (reliableMessage == null) {
            throw new ServiceException(CoreExceptionEnum.REQUEST_NULL);
        }
        if (ToolUtil.isEmpty(reliableMessage.getMessageId())) {
            throw new ServiceException(MessageExceptionEnum.MESSAGE_ID_CANT_EMPTY);
        }
        if (ToolUtil.isEmpty(reliableMessage.getMessageBody())) {
            throw new ServiceException(MessageExceptionEnum.MESSAGE_BODY_CANT_EMPTY);
        }
        if (ToolUtil.isEmpty(reliableMessage.getConsumerQueue())) {
            throw new ServiceException(MessageExceptionEnum.QUEUE_CANT_EMPTY);
        }
    }

}
