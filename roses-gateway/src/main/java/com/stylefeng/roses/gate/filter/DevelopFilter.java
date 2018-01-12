package com.stylefeng.roses.gate.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.stylefeng.roses.core.constant.Constant;
import com.stylefeng.roses.gate.config.properties.JwtProperties;
import com.stylefeng.roses.gate.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 开发时候用的网关过滤器
 * 作用:
 * 1.可以让开发时,不用token就可以获取服务器资源
 * 2.可以传给下游服务用户id,默认用户id为1
 *
 * @author fengshuonan
 * @date 2017-11-10-下午2:32
 */
public class DevelopFilter extends ZuulFilter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    JwtProperties jwtProperties;

    @Value("${develop.user-id}")
    private String userId;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        currentContext.addZuulRequestHeader(Constant.IDENTITY_HEADER, userId);
        currentContext.set(Constant.IDENTITY_HEADER, userId);
        return null;
    }
}
