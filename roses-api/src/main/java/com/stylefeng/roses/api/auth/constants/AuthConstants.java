package com.stylefeng.roses.api.auth.constants;

/**
 * auth模块常量
 *
 * @author fengshuonan
 * @date 2018-02-06 16:18
 * Copyright: 2018赛鼎科技-版权所有
 */
public interface AuthConstants {

    /**
     * 资源服务名称
     */
    String RESOURCE_SERVICE_NAME = "resourceService";

    /**
     * auth服务名称
     */
    String AUTH_SERVICE_NAME = "authService";

    /**
     * 登录的地址
     */
    String SHARE_AUTH_URL = "/shareAuth";

    /**
     * 验证token是否正确
     */
    String VALIDATE_TOKEN_URL = "/shareValidate";

    /**
     * 注销的地址
     */
    String SHARE_LOGOUT_URL = "/shareLogout";
}
