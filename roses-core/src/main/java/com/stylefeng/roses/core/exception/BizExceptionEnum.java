package com.stylefeng.roses.core.exception;

/**
 * @Description 所有业务异常的枚举
 * @author fengshuonan
 * @date 2016年11月12日 下午5:04:51
 */
public enum BizExceptionEnum {

	/**
	 * token异常
	 */
	TOKEN_EXPIRED(700, "token过期"),
	TOKEN_ERROR(700, "token验证失败"),
	PERMISSION_ERROR(800,"没有访问该资源的权限"),

	/**
	 * 签名异常
	 */
	SIGN_ERROR(700, "签名验证失败"),

	/**
	 * 其他
	 */
	WRITE_ERROR(500,"渲染界面错误"),

	/**
	 * 文件上传
	 */
	FILE_READING_ERROR(400,"FILE_READING_ERROR!"),
	FILE_NOT_FOUND(400,"FILE_NOT_FOUND!"),

	/**
	 * 错误的请求
	 */
	REQUEST_NULL(400, "请求有错误"),
	SERVER_ERROR(500, "服务器异常");

	BizExceptionEnum(int code, String message) {
		this.friendlyCode = code;
		this.friendlyMsg = message;
	}

	private int friendlyCode;

	private String friendlyMsg;

	public int getCode() {
		return friendlyCode;
	}

	public void setCode(int code) {
		this.friendlyCode = code;
	}

	public String getMessage() {
		return friendlyMsg;
	}

	public void setMessage(String message) {
		this.friendlyMsg = message;
	}

}
