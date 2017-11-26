package com.stylefeng.roses.core.exception;

/**
 * @author fengshuonan
 * @Description 业务异常的封装
 * @date 2016年11月12日 下午5:05:10
 */
public class BizException extends RuntimeException {

    //错误码
    private Integer code = 500;

    //友好提示
    private String friendlyMsg;

    //附带的对象信息
    private Object obj;

    public BizException(String friendlyMsg) {
        super(friendlyMsg);
        this.friendlyMsg = friendlyMsg;
    }

    public BizException(String friendlyMsg, Object obj) {
        super(friendlyMsg);
        this.friendlyMsg = friendlyMsg;
        this.obj = obj;
    }

    public BizException(Integer code, String friendlyMsg) {
        super(friendlyMsg);
        this.code = code;
        this.friendlyMsg = friendlyMsg;
    }

    public BizException(Integer code, String friendlyMsg, Object object) {
        super(friendlyMsg);
        this.friendlyMsg = friendlyMsg;
        this.obj = obj;
        this.code = code;
    }

    public BizException(BizExceptionEnum bizExceptionEnum) {
        super(bizExceptionEnum.getMessage());
        this.friendlyMsg = bizExceptionEnum.getMessage();
    }

    public String getFriendlyMsg() {
        return friendlyMsg;
    }

    public void setFriendlyMsg(String friendlyMsg) {
        this.friendlyMsg = friendlyMsg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
