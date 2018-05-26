package com.stylefeng.roses.core.util;

import com.stylefeng.roses.core.xss.WafRequestWrapper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 快捷获取HttpServletRequest,HttpServletResponse
 *
 * @author stylefeng
 * @Date 2018/1/4 21:24
 */
public class HttpContext {

    public static String getIp() {
        return HttpContext.getRequest().getRemoteHost();
    }

    public static HttpServletResponse getResponse() throws NullPointerException {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static HttpServletRequest getRequest() throws NullPointerException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new WafRequestWrapper(request);
    }

}
