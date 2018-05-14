package com.stylefeng.roses.api.auth.service;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import share.model.auth.LoginUser;

/**
 * 鉴权服务,提供颁发,校验,注销等方法
 *
 * @author fengshuonan
 * @date 2018-02-07 9:57
 * Copyright: 2018赛鼎科技-版权所有
 */
@RequestMapping("/api/authService")
public interface AuthService {

    /**
     * <p>登录(验证账号密码)</p>
     * <p>若登录成功则返回token,若登录不成功则返回null</p>
     *
     * @author fengshuonan
     * @Date 2018/1/12 13:56
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String login(@RequestParam("account") String account, @RequestParam("password") String password);

    /**
     * 校验token(true-校验成功,false-校验失败)
     *
     * @author fengshuonan
     * @Date 2018/2/7 10:02
     */
    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    boolean checkToken(@RequestParam("token") String token);

    /**
     * 注销token
     *
     * @author fengshuonan
     * @Date 2018/2/7 10:02
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    void logout(@RequestParam("token") String token);

    /**
     * 通过token获取用户信息
     *
     * @author fengshuonan
     * @Date 2018/1/12 16:32
     */
    @RequestMapping(value = "/getLoginUserByToken", method = RequestMethod.POST)
    LoginUser getLoginUserByToken(@RequestParam("token") String token);

    /**
     * 刷新当前用户登录的公司
     *
     * @author fengshuonan
     * @Date 2018/4/2 14:31
     */
    @RequestMapping(value = "/refreshCompanyId", method = RequestMethod.POST)
    void refreshCompanyId(@RequestParam("token") String token, @RequestParam("companyId") String companyId);

    /**
     * 刷新当前用户登录的应用
     *
     * @author fengshuonan
     * @Date 2018/4/2 14:31
     */
    @RequestMapping(value = "/refreshAppId", method = RequestMethod.POST)
    void refreshAppId(@RequestParam("token") String token, @RequestParam("appId") String appId);

}
