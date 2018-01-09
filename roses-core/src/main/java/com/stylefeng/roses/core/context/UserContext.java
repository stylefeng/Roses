package com.stylefeng.roses.core.context;

import com.stylefeng.roses.api.auth.model.LoginUser;
import com.stylefeng.roses.core.util.HttpContext;
import com.stylefeng.roses.core.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 获取当前登录用户信息的工具类
 *      注意: 本类提供给auth模块以外的模块调用,auth模块自己查库获取当前登录用户
 *
 * </pre>
 *
 * @author fengshuonan
 * @date 2017-11-09-下午5:38
 */
@Component
public class UserContext {

    @Autowired
    private AuthServiceConsumer authServiceConsumer;

    /**
     * 获取UserContext bean
     *
     * @author fengshuonan
     * @Date 2017/11/9 下午7:35
     */
    public static UserContext me() {
        return SpringContextHolder.getBean(UserContext.class);
    }

    /**
     * <pre>
     * 获取当前登录用户,两种情况:
     *  1.auth以外的服务调用本发放走远程调用
     *  2.auth服务调用这个方法,自己调用内部service
     * </pre>
     *
     * @author fengshuonan
     * @Date 2017/11/9 下午7:26
     */
    public LoginUser getUser() {
        String userId = HttpContext.getRequest().getHeader(RosesConst.IDENTITY_HEADER);
        LoginUser loginUser = authServiceConsumer.getUserById(Long.valueOf(userId));
        return loginUser;
    }
}
