package com.stylefeng.roses.facade.api;

import com.stylefeng.roses.facade.entity.ServiceMessage;

/**
 * 消息服务子系统开放的接口
 *
 * @author fengshuonan
 * @date 2017-05-29 22:43
 */
public interface MessageServiceApi {

    /**
     * 预存储消息
     *
     * @author stylefeng
     * @Date 2017/6/2 22:32
     */
    boolean saveMessageWaitingConfirm(ServiceMessage serviceMessage);

}
