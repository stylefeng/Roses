package com.stylefeng.roses.auth.modular.provider;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.roses.api.auth.AuthServiceApi;
import com.stylefeng.roses.api.auth.model.LoginUser;
import com.stylefeng.roses.auth.modular.entity.Secret;
import com.stylefeng.roses.auth.modular.mapper.SecretMapper;
import com.stylefeng.roses.auth.modular.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    private ISysUserService sysUserService;

    @Autowired
    private SecretMapper secretMapper;

    @Override
    public LoginUser getUserById(@RequestParam("userId") Long userId) {
        return sysUserService.getUserLoginInfo(userId);
    }

    @Override
    public Set<String> getUserPermissionUrls(@RequestParam("userId") Long userId) {
        return sysUserService.getPermissionUrlsByUserId(userId);
    }

    @Override
    public String getSecretByAppId(@RequestParam("appId") String appId) {
        List<Secret> secrets = secretMapper.selectList(
                new EntityWrapper<Secret>().eq("app_id", appId));
        if (secrets != null && !secrets.isEmpty()) {
            return secrets.get(0).getSecret();
        } else {
            return null;
        }
    }
}
