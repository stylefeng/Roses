package com.stylefeng.roses.api.auth.model;

/**
 * 登录中的用户信息
 *
 * @author fengshuonan
 * @date 2017-11-09-下午5:47
 */
public class LoginUser {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户姓名
     */
    private String phoneNumber;

    /**
     * email
     */
    private String email;

    /**
     * 状态: 1-启用  0-禁用
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
