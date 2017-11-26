package com.stylefeng.roses.core.base;

/**
 * 封装json结果集
 */
public class JsonResult {

    private Boolean success = false;          // 返回是否成功
    private String msg = "";                  // 返回信息
    private Object obj = null;                // 返回其他对象信息

    public JsonResult() {
    }

    public JsonResult(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public JsonResult(Boolean success, String msg, Object obj) {
        this.success = success;
        this.msg = msg;
        this.obj = obj;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    // ==========  static methods

    public static JsonResult resultError() {
        return resultError("网络异常");
    }

    public static JsonResult resultError(String message) {
        return new JsonResult(false, message);
    }

    public static JsonResult resultSuccess() {
        return resultSuccess("操作成功", null);
    }

    public static JsonResult resultSuccess(Object obj) {
        return resultSuccess("操作成功", obj);
    }

    public static JsonResult resultSuccess(String message, Object obj) {
        return new JsonResult(true, message, obj);
    }
}
