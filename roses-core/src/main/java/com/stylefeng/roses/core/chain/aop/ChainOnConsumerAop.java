package com.stylefeng.roses.core.chain.aop;

import com.stylefeng.roses.api.common.exception.ServiceException;
import com.stylefeng.roses.core.chain.context.ParentSpanIdHolder;
import com.stylefeng.roses.core.chain.context.SpanIdHolder;
import com.stylefeng.roses.core.chain.context.TraceIdHolder;
import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.core.util.TraceUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import static com.stylefeng.roses.core.chain.enums.RpcPhaseEnum.*;

/**
 * 基于调用链的服务治理系统的设计（feign远程调用层的aop处理）
 *
 * @author fengshuonan
 * @date 2018年05月15日14:58:44
 */
@Aspect
@Order(650)
public class ChainOnConsumerAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.stylefeng.*.*..consumer.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {

        MethodSignature methodSignature = null;
        Signature signature = point.getSignature();
        if (signature != null && signature instanceof MethodSignature) {
            methodSignature = (MethodSignature) signature;
        }

        long begin = System.currentTimeMillis();
        if (logger.isTraceEnabled()) {
            logger.trace("开始记录consumer aop耗时！");
        }

        //获取当前节点的spanId
        String currentSpanId = SpanIdHolder.get();

        //获取当前节点的parentSpanId
        String parentSpanId = ParentSpanIdHolder.get();

        //获取traceId
        String traceId = TraceIdHolder.get();

        if (logger.isTraceEnabled()) {
            logger.debug("consumer aop 获取参数！" + (System.currentTimeMillis() - begin));
        }

        try {

            //报告:开始调用远程服务
            TraceUtil.trace(methodSignature, P1, traceId, currentSpanId, parentSpanId);

            if (logger.isTraceEnabled()) {
                logger.trace("consumer aop 开始调用远程服务前！" + (System.currentTimeMillis() - begin));
            }

            Object result = point.proceed();

            if (logger.isTraceEnabled()) {
                logger.trace("consumer aop 调用完远程服务！" + (System.currentTimeMillis() - begin));
            }

            //报告:调用远程服务成功
            TraceUtil.trace(methodSignature, P4, traceId, currentSpanId, parentSpanId);

            if (logger.isTraceEnabled()) {
                logger.trace("consumer aop 发送调用成功！" + (System.currentTimeMillis() - begin));
            }

            return result;

        } catch (ServiceException serviceException) {

            String exceptionMsg = ToolUtil.getExceptionMsg(serviceException);

            //报告:调用远程服务失败
            TraceUtil.trace(methodSignature, EP4, traceId, currentSpanId, parentSpanId, exceptionMsg);

            if (logger.isTraceEnabled()) {
                logger.trace("consumer aop 记录完错误日志！" + (System.currentTimeMillis() - begin));
            }

            throw serviceException;

        } catch (Throwable exception) {

            String exceptionMsg = ToolUtil.getExceptionMsg(exception);

            //报告:调用远程服务失败
            TraceUtil.trace(methodSignature, EP4, traceId, currentSpanId, parentSpanId, exceptionMsg);

            if (logger.isTraceEnabled()) {
                logger.trace("consumer aop 记录完错误日志！" + (System.currentTimeMillis() - begin));
            }

            throw exception;

        }

    }
}
