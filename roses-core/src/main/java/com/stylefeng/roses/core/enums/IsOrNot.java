package com.stylefeng.roses.core.enums;

/**
 * 是或否的枚举
 *
 * @author stylefeng
 * @Date 2017/6/2 23:41
 */
public enum IsOrNot {

	YES("是"),

	NO("否");

	private String desc;

	IsOrNot(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
