package com.stylefeng.roses.api.auth.service;

/**
 * 签名服务
 *
 * @author fengshuonan
 * @date 2017-12-25 22:24
 */
public interface SignService {

    /**
     * 生成签名
     */
    String generateSign(String appId, String timestamp, String data);

    /**
     * 查找appSecret
     */
    String getAppSecret(String appId);

    /**
     * 验证sign签名
     */
    Boolean validateSign(String appId, String timestamp, String data, String requestSignToken);
}
