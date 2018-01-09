package com.stylefeng.roses.auth.modular.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-09
 */
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 账号
     */
	private String account;
    /**
     * 密码
     */
	private String password;
    /**
     * 密码盐
     */
	private String salt;
    /**
     * 电话号码
     */
	@TableField("phone_number")
	private String phoneNumber;
    /**
     * 电子邮件
     */
	private String email;
    /**
     * 性别: F-女；M-男
     */
	private String sex;
    /**
     * 状态: 1:启用 0:禁用
     */
	private Integer status;
    /**
     * 用户姓名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysUser{" +
			"id=" + id +
			", account=" + account +
			", password=" + password +
			", salt=" + salt +
			", phoneNumber=" + phoneNumber +
			", email=" + email +
			", sex=" + sex +
			", status=" + status +
			", userName=" + userName +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
