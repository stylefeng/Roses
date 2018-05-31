package com.stylefeng.roses.core.config.properties;

/**
 * 日志记录的参数配置
 *
 * @author fengshuonan
 * @Date 2018/5/26 下午2:16
 */
public class LogProperties {

    /**
     * 记录日志的级别（逗号隔开）
     */
    private String level = "error,info";

    /**
     * 是否开启trace链式记录
     */
    private Boolean trace = true;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getTrace() {
        return trace;
    }

    public void setTrace(Boolean trace) {
        this.trace = trace;
    }
}
