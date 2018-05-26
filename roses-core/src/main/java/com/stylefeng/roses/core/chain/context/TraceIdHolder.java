package com.stylefeng.roses.core.chain.context;

/**
 * 基于调用链的服务治理系统的设计（requestNo以及当前节点的spanId和parentSpanId的临时存储器）
 *
 * @author fengshuonan
 * @date 2017年3月5日 上午9:10:58
 */
public class TraceIdHolder {

    private static final ThreadLocal<String> TRACE_ID_CONTEXT = new ThreadLocal<>();

    public static void set(String traceId) {
        TRACE_ID_CONTEXT.set(traceId);
    }

    public static String get() {
        return TRACE_ID_CONTEXT.get();
    }

    public static void remove() {
        TRACE_ID_CONTEXT.remove();
    }
}
