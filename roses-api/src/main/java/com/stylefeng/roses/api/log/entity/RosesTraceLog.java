package com.stylefeng.roses.api.log.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * 日志链实体类
 *
 * @author yaoliguo
 * @date 2018-05-16 09:40
 */
@TableName("roses_trace_log")
public class RosesTraceLog extends Model<RosesTraceLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    /**
     * 应用名称
     */
    @TableField("APPLICATION_NAME")
    private String applicationName;
    /**
     * 方法名称
     */
    @TableField("METHOD_NAME")
    private String methodName;
    /**
     * 请求路径
     */
    @TableField("SERVLET_PATH")
    private String servletPath;
    /**
     * rpc调用类型，
    G1,     //网关发送请求

    G2,     //接收网关请求（切controller）

    P1,     //调用端发送请求（切consumer）

    P2,     //被调用端接收到请求（切provider）

    P3,     //被调用端发送响应成功

    P4,     //调用端接收到响应成功

    EP3,    //被调用端发送响应失败

    EP4,    //调用端接收到响应失败

    G3,     //控制器响应网关成功

    G4,     //网关接收到成功请求

    EG3,    //控制器接收到错误响应

    EG4,    //网关接收到错误响应
     */
    @TableField("RPC_PHASE")
    private String rpcPhase;
    /**
     * 唯一请求号
     */
    @TableField("TRACE_ID")
    private String traceId;
    /**
     * 节点id
     */
    @TableField("SPAN_ID")
    private String spanId;
    /**
     * 节点父id
     */
    @TableField("PARENT_SPAN_ID")
    private String parentSpanId;
    /**
     * 生成时间戳
     */
    @TableField("CREATE_TIME")
    private Long createTime;
    /**
     * 日志内容
     */
    @TableField("CONTENT")
    private String content;
    /**
     * ip地址
     */
    @TableField("IP")
    private String ip;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getServletPath() {
        return servletPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    public String getRpcPhase() {
        return rpcPhase;
    }

    public void setRpcPhase(String rpcPhase) {
        this.rpcPhase = rpcPhase;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(String parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RosesTraceLog{" +
        "id=" + id +
        ", applicationName=" + applicationName +
        ", methodName=" + methodName +
        ", servletPath=" + servletPath +
        ", rpcPhase=" + rpcPhase +
        ", traceId=" + traceId +
        ", spanId=" + spanId +
        ", parentSpanId=" + parentSpanId +
        ", createTime=" + createTime +
        ", content=" + content +
        ", ip=" + ip +
        "}";
    }
}
