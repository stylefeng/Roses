package com.stylefeng.roses.gate.filter;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.stylefeng.roses.api.common.constants.RosesConstants;
import com.stylefeng.roses.gate.constants.ZuulConstants;

import javax.servlet.http.HttpServletResponse;

/**
 * requestNo生成过滤器
 *
 * @author fengshuonan
 * @date 2017-11-08-下午2:49
 */
public class RequestNoGenerateFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return ZuulConstants.REQUEST_NO_GENERATE_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletResponse response = currentContext.getResponse();

        String requestNo = IdWorker.getIdStr();

        currentContext.addZuulRequestHeader(RosesConstants.REQUEST_NO_HEADER_NAME, requestNo);

        response.addHeader(RosesConstants.REQUEST_NO_HEADER_NAME, requestNo);

        return null;
    }
}
