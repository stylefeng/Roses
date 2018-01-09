package com.stylefeng.roses.auth.modular.provider;

import com.stylefeng.roses.api.auth.api.AuthServiceApi;
import com.stylefeng.roses.api.auth.model.LoginUser;
import com.stylefeng.roses.auth.modular.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * auth接口实现
 *
 * @author fengshuonan
 * @date 2017-11-09-下午7:47
 */
@RestController
public class AuthServiceProvider implements AuthServiceApi {

    @Autowired
    ISysUserService sysUserService;

    @Override
    public LoginUser getUserById(Long userId) {
        return sysUserService.getUserLoginInfo(userId);
    }

    @Override
    public Set<String> getUserPermissionUrls(Long userId) {
        return sysUserService.getPermissionUrlsByUserId(userId);
    }
}
