package com.stylefeng.roses.core.config.properties;

/**
 * 签名校验需要的配置
 *
 * @author fengshuonan
 * @Date 2018/5/8 下午2:24
 */
public class SignProperties {

    /**
     * 签名失效时间
     */
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}