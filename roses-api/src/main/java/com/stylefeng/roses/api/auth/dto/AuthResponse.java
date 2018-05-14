package com.stylefeng.roses.api.auth.dto;

import java.io.Serializable;

/**
 * 认证的响应结果
 *
 * @author fengshuonan
 * @Date 2017/8/24 13:58
 */
public class AuthResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
