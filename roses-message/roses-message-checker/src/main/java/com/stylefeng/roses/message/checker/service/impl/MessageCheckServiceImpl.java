package com.stylefeng.roses.message.checker.service.impl;

import com.stylefeng.roses.core.util.LogUtil;
import com.stylefeng.roses.message.checker.service.MessageCheckService;

/**
 * 消息校验重发服务
 *
 * @author fengshuonan
 * @date 2018-05-07 23:03
 */
public class MessageCheckServiceImpl implements MessageCheckService {

    @Override
    public void disposeWaitingConfirmTimeOutMessages() {
        try {

            int numPerPage = 2000;      // 每页条数
            int maxHandlePageCount = 3; // 一次最多处理页数


        } catch (Exception e) {
            LogUtil.error("");
        }


    }

    @Override
    public void disposeSendingTimeOutMessage() {

    }
}
