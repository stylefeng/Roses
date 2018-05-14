package com.stylefeng.roses.api.auth.dto;


import java.io.Serializable;

/**
 * 退出的请求参数封装
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:00
 */
public class LogoutRequest implements Serializable{

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
