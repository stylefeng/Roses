package com.stylefeng.roses.core.log;


import com.stylefeng.roses.api.log.entity.RosesCommanLog;
import com.stylefeng.roses.api.log.entity.RosesTraceLog;

/**
 * 发送日志到消息队列的接口类
 *
 * @author yaoliguo
 * @date 2018-04-25 10:37
 */
public interface LogProducerService {

    /**
     * 发送日志
     *
     * @author yaoliguo
     * @date 2018-04-25 10:37
     */
    void sendMsg(RosesCommanLog log);

    /**
     * 发送trace日志
     *
     * @author fengshuonan
     * @Date 2018/5/15 下午7:16
     */
    void sendTraceMsg(RosesTraceLog traceLog);

}
