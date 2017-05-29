package com.stylefeng.roses.persistence.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2017-05-29
 */
public class Message extends Model<Message> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private String id;
    /**
     * 版本号
     */
	private Integer version;
    /**
     * 修改者
     */
	private String editor;
    /**
     * 创建者
     */
	private String creater;
    /**
     * 最后修改时间
     */
	@TableField("edit_time")
	private Date editTime;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
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
     */
	@TableField("areadly_dead")
	private String areadlyDead;
    /**
     * 状态
     */
	private String status;
    /**
     * 备注
     */
	private String remark;
    /**
     * 扩展字段1
     */
	private String field1;
    /**
     * 扩展字段2
     */
	private String field2;
    /**
     * 扩展字段3
     */
	private String field3;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
