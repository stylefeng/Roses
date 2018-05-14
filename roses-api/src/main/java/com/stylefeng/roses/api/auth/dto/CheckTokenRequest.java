package com.stylefeng.roses.api.auth.dto;

/**
 * 校验token的请求
 *
 * @author fengshuonan
 * @date 2018-02-28 10:03
 * Copyright: 2018赛鼎科技-版权所有
 */
public class CheckTokenRequest {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
