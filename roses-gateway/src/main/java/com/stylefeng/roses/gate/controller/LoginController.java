package com.stylefeng.roses.gate.controller;

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

    @RequestMapping("")
    @ResponseBody
    public String login(){
        return "login page";
    }
}
