package com.stylefeng.roses.gate.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.stylefeng.roses.api.common.exception.CoreExceptionEnum;
import com.stylefeng.roses.api.common.exception.ServiceException;
import com.stylefeng.roses.core.constant.Constant;
import com.stylefeng.roses.gate.config.properties.JwtProperties;
import com.stylefeng.roses.gate.constants.ZuulConstants;
import com.stylefeng.roses.gate.utils.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限校验的过滤器
 *
 * @author fengshuonan
 * @date 2017-11-08-下午2:49
 */
public class JwtTokenFilter extends ZuulFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return ZuulConstants.JWT_TOKEN_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        //是否是登录页面
        if (request.getServletPath().equals(jwtProperties.getAuthPath())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();

        final String authHeader = request.getHeader(jwtProperties.getHeader());
        String authToken = null;
        if (authHeader != null && authHeader.startsWith(JwtProperties.AUTH_HEADER_PREFIX)) {
            authToken = authHeader.substring(JwtProperties.AUTH_HEADER_PREFIX.length());

            //验证token是否过期,包含jwt是否正确
            try {
                boolean flagExpired = jwtTokenUtil.isTokenExpired(authToken);
                if (flagExpired) {
                    throw new ServiceException(CoreExceptionEnum.TOKEN_ERROR);
                } else {
                    //验证成功,将用户id传给下游服务
                    String userId = jwtTokenUtil.getUserIdFromToken(authToken);
                    currentContext.addZuulRequestHeader(Constant.IDENTITY_HEADER, userId);
                    currentContext.set(Constant.IDENTITY_HEADER, userId);
                    return null;
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                throw new ServiceException(CoreExceptionEnum.TOKEN_ERROR);
            }
        } else {
            //header authorization为空，或者没有带Bearer字段
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }
    }
}
