package com.stylefeng.roses.core.util;

import com.stylefeng.roses.api.auth.model.LoginUser;
import com.stylefeng.roses.api.log.entity.RosesCommanLog;
import com.stylefeng.roses.core.base.request.RequestData;
import com.stylefeng.roses.core.config.properties.LogProperties;
import com.stylefeng.roses.core.context.LoginContext;
import com.stylefeng.roses.core.context.RequestDataHolder;
import com.stylefeng.roses.core.context.RequestNoContext;
import com.stylefeng.roses.core.log.LogProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 日志记录工具
 *
 * @author fengshuonan
 * @date 2018-01-16 15:00
 */
public class LogUtil {

    private static LogProducerService getLogProducer() {
        return SpringContextHolder.getBean(LogProducerService.class);
    }

    private static LogProperties getLogProperties() {
        return SpringContextHolder.getBean(LogProperties.class);
    }

    public static void info(String message) {
        doLog(LogLevel.INFO, RequestDataHolder.get(), message, null);
    }

    public static void error(String message) {
        doLog(LogLevel.ERROR, RequestDataHolder.get(), message, null);
    }

    public static void error(String message, Throwable exception) {
        doLog(LogLevel.ERROR, RequestDataHolder.get(), message, exception);
    }

    public static void debug(String message) {
        doLog(LogLevel.DEBUG, RequestDataHolder.get(), message, null);
    }

    public static void trace(String message) {
        doLog(LogLevel.TRACE, RequestDataHolder.get(), message, null);
    }

    public static void warn(String message) {
        doLog(LogLevel.WARN, RequestDataHolder.get(), message, null);
    }

    public static void debug(String requestNo, String message) {
        doLog(requestNo, LogLevel.DEBUG, RequestDataHolder.get(), message, null);
    }

    public static void info(String requestNo, String message) {
        doLog(requestNo, LogLevel.INFO, RequestDataHolder.get(), message, null);
    }

    /**
     * 记录日志(不需要传递请求号)
     *
     * @author fengshuonan
     * @Date 2018/1/16 15:02
     */
    private static void doLog(LogLevel level, RequestData requestData, String message, Throwable exception) {
        doLog(RequestNoContext.getRequestNo(), level, requestData, message, exception);
    }

    /**
     * 记录日志(需要传递请求号，目前用在网关)
     *
     * @author fengshuonan
     * @Date 2018/1/16 15:02
     */
    private static void doLog(String requestNo, LogLevel level, RequestData requestData, String message, Throwable exception) {

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
                    logger.info(createMessage(requestData, message));
                    break;
                case WARN:
                    logger.warn(createMessage(requestData, message));
                    break;
                case DEBUG:
                    logger.debug(createMessage(requestData, message));
                    break;
                case ERROR:
                    logger.error(createMessage(requestData, message), exception);
                    break;
                case TRACE:
                    logger.trace(createMessage(requestData, message));
                    break;
                default:
                    logger.debug(createMessage(requestData, message));
                    break;
            }

            //将日志记录到数据库
            if (isWriteLog(level)) {

                RosesCommanLog log = new RosesCommanLog();

                if (requestData != null) {
                    if (requestData.getData() != null) {
                        log.setRequestData(requestData.getData().toJSONString());
                    }
                    log.setIp(ToolUtil.getIP());
                    log.setUrl(requestData.getUrl());
                }

                LoginUser user = null;

                try {
                    user = LoginContext.me().getUser();
                } catch (Exception e) {
                    user = new LoginUser();
                }

                log.setLogNum(requestNo);
                log.setClassName(className);
                log.setLogCategory(level.name());
                log.setLogContent(message);
                log.setCreateTime(System.currentTimeMillis());
                log.setApplicationName(ToolUtil.getApplicationName());

                if (level.equals(LogLevel.ERROR) && exception != null) {
                    log.setLogDetails(getErrorInfoFromException(exception));
                }
                getLogProducer().sendMsg(log);
            }

        } catch (ClassNotFoundException e) {
            Logger logger = LoggerFactory.getLogger(LogUtil.class);
            logger.error("记录日志出错,找不到调用类名称!", e);
        }
    }

    /**
     * 堆栈异常信息转化为字符串形式
     *
     * @author yaoliguo
     * @date 2018-05-02 16:23
     */
    private static String getErrorInfoFromException(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            e.printStackTrace(pw);
            return sw.getBuffer().toString().replaceAll("\\$", "T");
        } catch (Exception e2) {
            return "ErrorInfoFromException";
        } finally {
            try {
                sw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            pw.close();
        }
    }

    private static boolean isWriteLog(LogLevel level) {

        String pro = getLogProperties().getLevel();
        if (pro == null || "".equals(pro.trim())) {
            pro = "info,error";
        }
        pro = pro.toUpperCase();

        String levelName = level.name();

        if (pro.contains(levelName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 创建日志内容
     */
    private static String createMessage(RequestData requestData, String message) {
        String requestNo = RequestNoContext.getRequestNo();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RequestNo: ");
        stringBuilder.append(requestNo);
        stringBuilder.append(" ==>> ");
        stringBuilder.append("Messages: ");
        stringBuilder.append(message);
        stringBuilder.append(" ==>> RequestData: ");
        stringBuilder.append(requestData);
        return stringBuilder.toString();
    }

    /**
     * 日志记录级别
     */
    public enum LogLevel {
        INFO, ERROR, WARN, DEBUG, TRACE
    }

}
