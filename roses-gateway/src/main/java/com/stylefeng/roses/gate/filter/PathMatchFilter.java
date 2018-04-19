package com.stylefeng.roses.gate.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.stylefeng.roses.api.common.exception.CoreExceptionEnum;
import com.stylefeng.roses.core.base.response.JsonResponse;
import com.stylefeng.roses.core.constant.Constant;
import com.stylefeng.roses.core.context.AuthServiceConsumer;
import com.stylefeng.roses.core.util.RenderUtil;
import com.stylefeng.roses.gate.config.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 请求路径权限过滤器
 *
 * @author fengshuonan
 * @date 2017-11-14-上午10:43
 */
public class PathMatchFilter extends ZuulFilter {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private AuthServiceConsumer authServiceConsumer;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
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

        String userId = (String) currentContext.get(Constant.IDENTITY_HEADER);
        Set<String> permissionUrls = authServiceConsumer.getUserPermissionUrls(Long.valueOf(userId));

        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean hasPermission = false;
        for (String permission : permissionUrls) {
            hasPermission = antPathMatcher.match(permission, request.getServletPath());
            if (hasPermission == true) {
                break;
            }
        }

        if (hasPermission == true) {
            return null;
        } else {
            currentContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            currentContext.setSendZuulResponse(false);
            RenderUtil.renderJson(response, JsonResponse.error(CoreExceptionEnum.PERMISSION_ERROR.getMessage()));
            return null;
        }
    }
}
