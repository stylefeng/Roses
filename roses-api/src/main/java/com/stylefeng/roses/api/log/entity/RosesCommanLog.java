package com.stylefeng.roses.api.log.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 日志实体类
 *
 * @author yaoliguo
 * @date 2018-04-24 13:45
 */
@TableName("roses_common_log")
public class RosesCommanLog extends Model<RosesCommanLog> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    /**
     * 日志id
     */
    @TableField("LOG_ID")
    private String logId;
    /**
     * 应用id
     */
    @TableField("APP_ID")
    private String appId;
    /**
     * spring.application.name的名称
     */
    @TableField("APPLICATION_NAME")
    private String applicationName;
    /**
     * 应用名称
     */
    @TableField("CLASS_NAME")
    private String className;

    /**
     * 服务端内网IP
     */
    @TableField("IP")
    private String ip;
    /**
     * 账号id
     */
    @TableField("ACCOUNT_ID")
    private String accountId;
    /**
     * 日志号
     */
    @TableField("LOG_NUM")
    private String logNum;
    /**
     * 日志名称
     */
    @TableField("URL")
    private String url;

    /**
     * 请求地址
     */
    @TableField("REQUEST_DATA")
    private String requestData;
    /**
     * 日志类别
     */
    @TableField("LOG_CATEGORY")
    private String logCategory;
    /**
     * 日志内容简要
     */
    @TableField("LOG_CONTENT")
    private String logContent;
    /**
     * 日志详情
     */
    @TableField("LOG_DETAILS")
    private String logDetails;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Long createTime;
    /**
     * Y:已删除   N:未删除
     */
    @TableField("DEL_FLAG")
    private String delFlag;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLogNum() {
        return logNum;
    }

    public void setLogNum(String logNum) {
        this.logNum = logNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getLogCategory() {
        return logCategory;
    }

    public void setLogCategory(String logCategory) {
        this.logCategory = logCategory;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LoggerContent{" +
                "logId='" + logId + '\'' +
                ", appId='" + appId + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", className='" + className + '\'' +
                ", ip='" + ip + '\'' +
                ", accountId='" + accountId + '\'' +
                ", logNum='" + logNum + '\'' +
                ", url='" + url + '\'' +
                ", requestData='" + requestData + '\'' +
                ", logCategory='" + logCategory + '\'' +
                ", logContent='" + logContent + '\'' +
                ", logDetails='" + logDetails + '\'' +
                ", createTime=" + createTime +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
