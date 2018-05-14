package com.stylefeng.roses.api.auth;

import java.io.Serializable;
import java.util.Date;

/**
 * api资源的声明类
 *
 * @author fengshuonan
 * @date 2018-01-03-下午3:27
 */
public class ResourceDefinition implements Serializable {

    /**
     * 应用的标识
     */
    private String appCode;

    /**
     * 控制器类名称
     */
    private String className;

    /**
     * 控制器中的方法名称
     */
    private String methodName;

    /**
     * 资源所属模块
     */
    private String modularCode;

    /**
     * 模块中文名称
     */
    private String modularName;

    /**
     * 资源的标识
     */
    private String code;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源的请求路径
     */
    private String url;

    /**
     * http请求方法
     */
    private String httpMethod;

    /**
     * 是否是菜单（true-是，false-否）
     */
    private Boolean menuFlag;

    /**
     * 是否需要登录
     */
    private Boolean requiredLogin;

    /**
     * 是否需要鉴权
     */
    private Boolean requiredPermission;

    /**
     * 资源添加日期
     */
    private Date createTime;

    /**
     * 初始化资源的机器的ip地址
     */
    private String ipAddress;

    public String getModularCode() {
        return modularCode;
    }

    public void setModularCode(String modularCode) {
        this.modularCode = modularCode;
    }

    public String getModularName() {
        return modularName;
    }

    public void setModularName(String modularName) {
        this.modularName = modularName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Boolean getMenuFlag() {
        return menuFlag;
    }

    public void setMenuFlag(Boolean menuFlag) {
        this.menuFlag = menuFlag;
    }

    public Boolean getRequiredLogin() {
        return requiredLogin;
    }

    public void setRequiredLogin(Boolean requiredLogin) {
        this.requiredLogin = requiredLogin;
    }

    public Boolean getRequiredPermission() {
        return requiredPermission;
    }

    public void setRequiredPermission(Boolean requiredPermission) {
        this.requiredPermission = requiredPermission;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "ResourceDefinition{" +
                "appCode='" + appCode + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", modularCode='" + modularCode + '\'' +
                ", modularName='" + modularName + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", menuFlag=" + menuFlag +
                ", requiredLogin=" + requiredLogin +
                ", requiredPermission=" + requiredPermission +
                '}';
    }
}
