package com.stylefeng.roses.core.context;

import com.stylefeng.roses.api.auth.model.LoginUser;
import com.stylefeng.roses.core.exception.CoreExceptionEnum;
import com.stylefeng.roses.core.exception.ServiceException;
import com.stylefeng.roses.core.util.HttpContext;
import com.stylefeng.roses.core.util.SpringContextHolder;

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
     * 获取当前登录用户
     *
     * @author fengshuonan
     * @Date 2018年1月11日22:02:24
     */
    public LoginUser getUser() {
        String userId = HttpContext.getRequest().getHeader(RosesConst.IDENTITY_HEADER);
        if (userId == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }
        AuthServiceConsumer authServiceConsumer = SpringContextHolder.getBean(AuthServiceConsumer.class);
        if (authServiceConsumer != null) {
            return authServiceConsumer.getUserById(Long.valueOf(userId));
        } else {
            return null;
        }
    }
}
