package com.stylefeng.roses.core.context;

import com.netflix.zuul.context.RequestContext;
import com.stylefeng.roses.api.auth.AuthServiceApi;
import com.stylefeng.roses.api.auth.model.LoginUser;
import com.stylefeng.roses.core.constant.Constant;
import com.stylefeng.roses.core.exception.CoreExceptionEnum;
import com.stylefeng.roses.core.exception.ServiceException;
import com.stylefeng.roses.core.util.HttpContext;
import com.stylefeng.roses.core.util.SpringContextHolder;
import com.stylefeng.roses.core.util.ToolUtil;
import org.springframework.beans.factory.annotation.Value;

/**
 * <pre>
 * 获取当前登录用户信息的工具类
 *      注意: 本类提供给auth和gateway模块以外的模块调用,auth模块自己查库获取当前登录用户
 * </pre>
 *
 * @author fengshuonan
 * @date 2018年1月11日22:02:32
 */
public class UserContext {

    @Value("${spring.application.name}")
    private String appName;

    /**
     * 获取UserContext bean
     *
     * @author fengshuonan
     * @Date 2018年1月11日22:02:28
     */
    public static UserContext me() {
        return SpringContextHolder.getBean(UserContext.class);
    }

    /**
     * <pre>
     * 获取当前登录用户,根据模块的不同,分为3种情况:
     *      1.gateway模块调用,调用auth模块接口获取
     *      2.auth模块调用,调用本模块中的service方法获取
     *      3.其他模块调用,调用auth模块接口获取
     * </pre>
     *
     * @author fengshuonan
     * @Date 2018年1月11日22:02:24
     */
    public LoginUser getUser() {
        if (Constant.GATEWAY_MODULAR_NAME.equals(appName)) {
            RequestContext currentContext = RequestContext.getCurrentContext();
            String userId = (String) currentContext.get(Constant.IDENTITY_HEADER);
            if (ToolUtil.isEmpty(userId)) {
                throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
            }
            AuthServiceConsumer authServiceConsumer = SpringContextHolder.getBean(AuthServiceConsumer.class);
            return authServiceConsumer.getUserById(Long.valueOf(userId));
        } else if (Constant.AUTH_MODULAR_NAME.equals(appName)) {
            AuthServiceApi authServiceApi = SpringContextHolder.getBean(AuthServiceApi.class);
            String userId = HttpContext.getRequest().getHeader(Constant.IDENTITY_HEADER);
            if (ToolUtil.isEmpty(userId)) {
                throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
            }
            return authServiceApi.getUserById(Long.valueOf(userId));
        } else {
            String userId = HttpContext.getRequest().getHeader(Constant.IDENTITY_HEADER);
            if (ToolUtil.isEmpty(userId)) {
                throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
            }
            AuthServiceConsumer authServiceConsumer = SpringContextHolder.getBean(AuthServiceConsumer.class);
            return authServiceConsumer.getUserById(Long.valueOf(userId));
        }
    }
}
