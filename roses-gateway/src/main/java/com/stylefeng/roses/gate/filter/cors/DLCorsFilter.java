package com.stylefeng.roses.gate.filter.cors;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  跨域过滤器
 * Created by Administrator on 2017/3/23.
 */

@WebFilter(filterName="DLCorsFilter",urlPatterns="/*")
public class DLCorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("*********************************过滤器初始化**************************");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        System.out.println("*********************************过滤器被使用**************************");
        chain.doFilter(req, res);
    }

    public void destroy() {
        System.out.println("*********************************过滤器被销毁**************************");
    }
}