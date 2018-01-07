package com.stylefeng.roses.auth.facade.api;

import com.stylefeng.roses.auth.facade.model.vo.LoginUser;

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
