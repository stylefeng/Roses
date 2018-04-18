package com.stylefeng.roses.api.message.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 消息服务
 * <p>
 * </p>
 *
 * @author stylefeng123
 * @since 2018-04-16
 */
@TableName("biz_message")
public class BizMessage extends Model<BizMessage> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 消息ID
     */
    @TableField("message_id")
    private String messageId;
    /**
     * 消息内容
     */
    @TableField("message_body")
    private String messageBody;
    /**
     * 消息数据类型
     */
    @TableField("message_data_type")
    private String messageDataType;
    /**
     * 消费队列
     */
    @TableField("consumer_queue")
    private String consumerQueue;
    /**
     * 消息重发次数
     */
    @TableField("message_send_times")
    private Integer messageSendTimes;
    /**
     * 是否死亡
     * <p>
     * Y：已死亡
     * N：未死亡
     */
    @TableField("areadly_dead")
    private String areadlyDead;
    /**
     * 状态
     * <p>
     * WAIT_VERIFY：待确认
     * SENDING：发送中
     */
    private String status;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime = new Date();
    /**
     * 最后修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 修改者
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 备注
     */
    private String remark;
    /**
     * 版本号
     */
    @Version
    private Long version = 0L;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageDataType() {
        return messageDataType;
    }

    public void setMessageDataType(String messageDataType) {
        this.messageDataType = messageDataType;
    }

    public String getConsumerQueue() {
        return consumerQueue;
    }

    public void setConsumerQueue(String consumerQueue) {
        this.consumerQueue = consumerQueue;
    }

    public Integer getMessageSendTimes() {
        return messageSendTimes;
    }

    public void setMessageSendTimes(Integer messageSendTimes) {
        this.messageSendTimes = messageSendTimes;
    }

    public String getAreadlyDead() {
        return areadlyDead;
    }

    public void setAreadlyDead(String areadlyDead) {
        this.areadlyDead = areadlyDead;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MessageParam{" +
                "id=" + id +
                ", messageId=" + messageId +
                ", messageBody=" + messageBody +
                ", messageDataType=" + messageDataType +
                ", consumerQueue=" + consumerQueue +
                ", messageSendTimes=" + messageSendTimes +
                ", areadlyDead=" + areadlyDead +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", remark=" + remark +
                ", version=" + version +
                "}";
    }
}
