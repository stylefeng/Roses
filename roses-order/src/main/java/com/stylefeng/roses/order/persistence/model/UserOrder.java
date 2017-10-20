package com.stylefeng.roses.order.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 用户订单表
 * </p>
 *
 * @author stylefeng
 * @since 2017-10-20
 */
@TableName("user_order")
public class UserOrder extends Model<UserOrder> {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("user_id")
	private Long userId;
	private String place;
	private String name;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserOrder{" +
			"id=" + id +
			", userId=" + userId +
			", place=" + place +
			", name=" + name +
			"}";
	}
}
