package com.stylefeng.roses.logger.listener;

import com.stylefeng.roses.api.log.constants.LogConstants;
import com.stylefeng.roses.api.log.entity.RosesCommanLog;
import com.stylefeng.roses.api.log.entity.RosesTraceLog;
import com.stylefeng.roses.logger.service.CommonLogService;
import com.stylefeng.roses.logger.service.TraceLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 日志处理消费类
 *
 * @author yaoliguo
 * @date 2018-04-24 16:12
 */
@Service
public class LoggerConsumerListener {

    @Autowired
    CommonLogService commonLogService;

    @Autowired
    TraceLogService traceLogService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final static Logger logger = LoggerFactory.getLogger(LoggerConsumerListener.class);

    @Scheduled(cron = "0/5 * * * * *")
    public void receiveQueue() {
        try {
            BoundListOperations<String, Object> options = redisTemplate.boundListOps(LogConstants.COMMON_LOG);
            List<RosesCommanLog> logList = new ArrayList<>();
            for (int i = 0; i < 500; i++) {
                RosesCommanLog log = (RosesCommanLog) options.rightPop();
                if (log != null && log.getCreateTime() != null) {
                    logList.add(log);
                } else {
                    break;
                }

            }
            if (logList.size() > 0) {
                commonLogService.insertBatch(logList, logList.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void receiveTraceLogQueue() {
        try {
            BoundListOperations<String, Object> options = redisTemplate.boundListOps(LogConstants.TRACE_LOG);
            List<RosesTraceLog> traceList = new ArrayList<>();
            for (int i = 0; i < 500; i++) {
                RosesTraceLog tracelog = (RosesTraceLog) options.rightPop();
                if (tracelog != null && tracelog.getCreateTime() != null) {
                    traceList.add(tracelog);
                } else {
                    break;
                }

            }
            if (traceList.size() > 0) {
                traceLogService.insertBatch(traceList, traceList.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
