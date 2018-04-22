package com.stylefeng.roses.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录工具
 *
 * @author fengshuonan
 * @date 2018-01-16 15:00
 */
public class LogUtil {

    public static void info(String message) {
        doLog(LogLevel.INFO, message, null);
    }

    public static void error(String message) {
        doLog(LogLevel.ERROR, message, null);
    }

    public static void error(String message, Throwable exception) {
        doLog(LogLevel.ERROR, message, exception);
    }

    public static void debug(String message) {
        doLog(LogLevel.DEBUG, message, null);
    }

    public static void trace(String message) {
        doLog(LogLevel.TRACE, message, null);
    }

    public static void warn(String message) {
        doLog(LogLevel.WARN, message, null);
    }

    /**
     * 记录日志
     *
     * @author fengshuonan
     * @Date 2018/1/16 15:02
     */
    private static void doLog(LogLevel level, String message, Throwable exception) {

        //获取被调用的类的名称
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        String className;

        if (stack == null || stack.length < 4) {
            className = LogUtil.class.getName();
        } else {
            className = stack[3].getClassName();
        }

        //记录日志
        try {
            Logger logger = LoggerFactory.getLogger(Class.forName(className));
            switch (level) {
                case INFO:
                    logger.info(createMessage(message));
                    break;
                case WARN:
                    logger.warn(createMessage(message));
                    break;
                case DEBUG:
                    logger.debug(createMessage(message));
                    break;
                case ERROR:
                    logger.error(createMessage(message), exception);
                    break;
                case TRACE:
                    logger.trace(createMessage(message));
                    break;
            }
        } catch (ClassNotFoundException e) {
            Logger logger = LoggerFactory.getLogger(LogUtil.class);
            logger.error("记录日志出错,找不到调用类名称!", e);
        }
    }

    /**
     * 创建日志内容
     */
    private static String createMessage(String message) {
        return "Messages: " + message;
    }

    /**
     * 日志记录级别
     */
    public enum LogLevel {
        INFO, ERROR, WARN, DEBUG, TRACE
    }

}