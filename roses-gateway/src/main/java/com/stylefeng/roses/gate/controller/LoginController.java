package com.stylefeng.roses.gate.controller;

import com.stylefeng.roses.api.message.model.ReliableMessage;
import com.stylefeng.roses.core.context.UserContext;
import com.stylefeng.roses.gate.consumer.MessageServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录控制器
 *
 * @author fengshuonan
 * @date 2017-11-08-下午7:04
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MessageServiceConsumer messageServiceConsumer;

    @RequestMapping("")
    @ResponseBody
    public Object login() {
        return UserContext.me().getUser();
    }

    @RequestMapping("/message")
    @ResponseBody
    public Object message() {
        ReliableMessage reliableMessage = new ReliableMessage();
        reliableMessage.setConsumerQueue("aaa");
        reliableMessage.setMessageBody("aa");
        ReliableMessage reliableMessage1 = messageServiceConsumer.preSaveMessage(reliableMessage);
        return reliableMessage1;
    }
}
