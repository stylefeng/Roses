package com.stylefeng.roses.api.common.enums;

/**
 * 是或者否的枚举
 *
 * @author stylefeng
 * @Date 2018/4/18 23:05
 */
public enum YseOrNotEnum {

    Y("是"),

    N("否");

    private String desc;

    YseOrNotEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
