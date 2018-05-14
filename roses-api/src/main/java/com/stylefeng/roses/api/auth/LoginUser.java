package com.stylefeng.roses.api.auth;

import java.io.Serializable;
import java.util.Set;

/**
 * 当前登录用户的信息
 *
 * @author fengshuonan
 * @date 2018年2月7日10:30:10
 */
public class LoginUser implements Serializable {

    /**
     * 用户id
     */
    private String infoId;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 账号id
     */
    private String accountId;

    /**
      * 账号
      *
      * @Author 戚传文
      * @Date 2018/3/29 12:20
      */
    private String account;

    /**
     * 电话号码
     */
    private String mobilePhone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 角色id
     */
    private Set<String> roleIds;

    /**
     * 组织id
     */
    private Set<String> organizationIds;

    /**
     * 包含的资源权限url
     */
    private Set<String> resourceUrls;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<String> roleIds) {
        this.roleIds = roleIds;
    }

    public Set<String> getOrganizationIds() {
        return organizationIds;
    }

    public void setOrganizationIds(Set<String> organizationIds) {
        this.organizationIds = organizationIds;
    }

    public Set<String> getResourceUrls() {
        return resourceUrls;
    }

    public void setResourceUrls(Set<String> resourceUrls) {
        this.resourceUrls = resourceUrls;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "infoId='" + infoId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", appId='" + appId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", accountId='" + accountId + '\'' +
                ", account='" + account + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", roleIds=" + roleIds +
                ", organizationIds=" + organizationIds +
                ", resourceUrls=" + resourceUrls +
                '}';
    }
}
