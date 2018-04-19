package com.stylefeng.roses.gate.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.stylefeng.roses.api.common.exception.CoreExceptionEnum;
import com.stylefeng.roses.api.common.exception.ServiceException;
import com.stylefeng.roses.core.base.response.JsonResponse;
import com.stylefeng.roses.core.constant.Constant;
import com.stylefeng.roses.core.util.RenderUtil;
import com.stylefeng.roses.gate.config.properties.JwtProperties;
import com.stylefeng.roses.gate.utils.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        return 2;
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

        final String requestHeader = request.getHeader(jwtProperties.getHeader());
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith(JwtProperties.AUTH_HEADER_PREFIX)) {
            authToken = requestHeader.substring(JwtProperties.AUTH_HEADER_PREFIX.length());

            //验证token是否过期,包含jwt是否正确
            try {
                boolean flagExpired = jwtTokenUtil.isTokenExpired(authToken);
                if (flagExpired) {
                    currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
                    currentContext.setSendZuulResponse(false);
                    RenderUtil.renderJson(response, JsonResponse.error(CoreExceptionEnum.TOKEN_EXPIRED.getMessage()));
                } else {
                    //验证成功,将用户id传给下游服务
                    String userId = jwtTokenUtil.getUserIdFromToken(authToken);
                    currentContext.addZuulRequestHeader(Constant.IDENTITY_HEADER, userId);
                    currentContext.set(Constant.IDENTITY_HEADER, userId);
                    return null;
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
                currentContext.setSendZuulResponse(false);
                RenderUtil.renderJson(response, JsonResponse.error(CoreExceptionEnum.TOKEN_ERROR.getMessage()));
            }
        } else {
            //header没有带Bearer字段
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            currentContext.setSendZuulResponse(false);
            try {
                currentContext.getResponse().sendRedirect(jwtProperties.getAuthPath());
            } catch (IOException e) {
                throw new ServiceException(CoreExceptionEnum.SERVICE_ERROR);
            }
        }
        return null;
    }
}
