package com.stylefeng.roses.api.auth;

import com.stylefeng.roses.api.auth.model.LoginUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * 用户中心接口
 *
 * @author stylefeng
 * @date 2017年11月09日19:38:34
 */
@RequestMapping("/api/authService")
public interface AuthServiceApi {

    /**
     * 根据用户id获取用户信息
     *
     * @author stylefeng
     * @Date 2017年11月09日19:38:51
     */
    @RequestMapping("/getUserById")
    LoginUser getUserById(@RequestParam("userId") Long userId);

    /**
     * 获取用户的权限路径
     *
     * @author stylefeng
     * @Date 2017/11/14 上午11:41
     */
    @RequestMapping("/getUserPermissionUrls")
    Set<String> getUserPermissionUrls(@RequestParam("userId") Long userId);

    /**
     * 通过appId获取secret
     *
     * @author stylefeng
     * @Date 2018/5/12 23:20
     */
    @RequestMapping("/getSecretByAppId")
    String getSecretByAppId(@RequestParam("appId") String appId);
}
