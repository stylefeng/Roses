package com.stylefeng.roses.gate.controller;

import com.stylefeng.roses.core.context.LoginContext;
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
@RequestMapping("/authService")
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public Object login() {
        return LoginContext.me().getUser();
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Object logout() {
        return null;
    }

    @RequestMapping("/checkToken")
    @ResponseBody
    public Object checkToken() {
        return null;
    }
}
