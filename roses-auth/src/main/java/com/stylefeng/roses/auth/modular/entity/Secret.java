package com.stylefeng.roses.auth.modular.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-12
 */
@TableName("sys_secret")
public class Secret extends Model<Secret> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 应用名称
     */
    @TableField("app_id")
    private String appId;
    /**
     * 签名密钥
     */
    private String secret;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Secret{" +
        "id=" + id +
        ", appId=" + appId +
        ", secret=" + secret +
        "}";
    }
}
