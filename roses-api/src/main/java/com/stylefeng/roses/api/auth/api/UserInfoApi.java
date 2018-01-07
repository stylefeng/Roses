package com.stylefeng.roses.api.auth.api;

import com.stylefeng.roses.api.auth.model.LoginUser;

/**
 * 获取用户详情的api
 *
 * @author fengshuonan
 * @date 2017-11-17-下午7:49
 */
public interface UserInfoApi {

    /**
     * 获取用户详情的api
     */
    LoginUser getUserById(Integer userId);
}
