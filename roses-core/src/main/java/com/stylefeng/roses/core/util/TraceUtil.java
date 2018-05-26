package com.stylefeng.roses.core.util;

import com.stylefeng.roses.api.log.entity.RosesTraceLog;
import com.stylefeng.roses.core.chain.enums.RpcPhaseEnum;
import com.stylefeng.roses.core.config.properties.LogProperties;
import com.stylefeng.roses.core.log.LogProducerService;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录工具
 *
 * @author fengshuonan
 * @date 2018-01-16 15:00
 */
public class TraceUtil {

    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void trace(
            MethodSignature methodSignature, RpcPhaseEnum rpcPhaseEnum, String traceId, String spanId, String parentSpanId) {
        trace(methodSignature, rpcPhaseEnum, traceId, spanId, parentSpanId, "");
    }

    public static void trace(
            MethodSignature methodSignature, RpcPhaseEnum rpcPhaseEnum, String traceId, String spanId, String parentSpanId, String errorMessage) {

        if (isTraceFlag()) {

            String servletPath = "";

            try {
                servletPath = HttpContext.getRequest().getServletPath();
            } catch (NullPointerException e) {
                //为空代表当前没有http请求
            }

            RosesTraceLog traceLog = new RosesTraceLog();
            traceLog.setIp(ToolUtil.getIP());
            traceLog.setApplicationName(ToolUtil.getApplicationName());
            traceLog.setCreateTime(System.currentTimeMillis());
            traceLog.setMethodName(methodSignature == null ? "" : methodSignature.getMethod().getName());
            traceLog.setParentSpanId(parentSpanId);
            traceLog.setSpanId(spanId);
            traceLog.setRpcPhase(rpcPhaseEnum.name());
            traceLog.setServletPath(servletPath);
            traceLog.setTraceId(traceId);
            traceLog.setContent(errorMessage);

            try {
                getLogProducer().sendTraceMsg(traceLog);
            } catch (Exception e) {
                logger.error("发送消息错误！", e);
            }
        }

    }

    private static LogProducerService getLogProducer() {
        return SpringContextHolder.getBean(LogProducerService.class);
    }

    private static LogProperties getLogProperties() {
        return SpringContextHolder.getBean(LogProperties.class);
    }

    private static boolean isTraceFlag() {

        Boolean traceFlag = null;

        try {
            traceFlag = getLogProperties().getTrace();
        } catch (Exception e) {
            logger.error("获取trace！", e);
            traceFlag = false;
        }

        if (traceFlag == null) {
            return false;
        } else {
            return traceFlag;
        }

    }
}
