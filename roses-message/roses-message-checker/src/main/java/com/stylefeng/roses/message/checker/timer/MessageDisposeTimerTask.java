package com.stylefeng.roses.message.checker.timer;

import com.stylefeng.roses.message.checker.service.impl.SendingMessageChecker;
import com.stylefeng.roses.message.checker.service.impl.WaitingConfirmMessageChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 消息定时检查计划任务
 *
 * @author fengshuonan
 * @date 2018-05-07 23:10
 */
@Component
public class MessageDisposeTimerTask {

    @Autowired
    private SendingMessageChecker sendingMessageChecker;

    @Autowired
    private WaitingConfirmMessageChecker waitingConfirmMessageChecker;

    /**
     * 定时检查“待确认”但已超时的消息
     *
     * @author stylefeng
     * @Date 2018/5/7 23:12
     */
    @Scheduled(fixedRate = 60000)
    public void checkWaitingConfirmTimeOutMessages() {

        waitingConfirmMessageChecker.checkMessages();

    }

    /**
     * 定时检查“发送中”但超时没有被成功消费确认的消息
     *
     * @author stylefeng
     * @Date 2018/5/7 23:12
     */
    @Scheduled(fixedRate = 60000)
    public void checkSendingTimeOutMessage() {

        sendingMessageChecker.checkMessages();

    }
}
