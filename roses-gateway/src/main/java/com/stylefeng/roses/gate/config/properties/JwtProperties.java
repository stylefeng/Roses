package com.stylefeng.roses.gate.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * jwt相关配置
 *
 * @author fengshuonan
 * @date 2017年11月08日16:58:15
 */
@Configuration
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
public class JwtProperties {

    public static final String JWT_PREFIX = "jwt";

    /**
     * jwt的开头标识
     */
    public static final String AUTH_HEADER_PREFIX = "Bearer ";

    /**
     * header中标识jwt的字段名称
     */
    private String header = "Authorization";

    /**
     * jwt的secret秘钥
     */
    private String secret = "defaultSecret";

    /**
     * jwt token过期时间,默认7天
     */
    private Long expiration = 604800L;

    /**
     * 请求人证的路径,可以理解为登录地址
     */
    private String authPath = "/auth";

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getAuthPath() {
        return authPath;
    }

    public void setAuthPath(String authPath) {
        this.authPath = authPath;
    }
}
