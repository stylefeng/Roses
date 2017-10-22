package com.stylefeng.roses.user.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2017-10-22
 */
@TableName("roses_user")
public class RosesUser extends Model<RosesUser> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long name;
	private BigDecimal integral;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getName() {
		return name;
	}

	public void setName(Long name) {
		this.name = name;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "RosesUser{" +
			"id=" + id +
			", name=" + name +
			", integral=" + integral +
			"}";
	}
}
