package com.stylefeng.roses.auth.modular.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.roses.api.auth.model.LoginUser;
import com.stylefeng.roses.auth.modular.entity.SysPermission;
import com.stylefeng.roses.auth.modular.entity.SysUser;
import com.stylefeng.roses.auth.modular.mapper.SysUserMapper;
import com.stylefeng.roses.auth.modular.service.ISysRoleService;
import com.stylefeng.roses.auth.modular.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-09
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    ISysRoleService sysRoleService;

    @Override
    public LoginUser getUserLoginInfo(Long userId) {
        SysUser sysUser = this.selectById(userId);
        if (sysUser != null) {
            LoginUser loginUser = new LoginUser();
            loginUser.setId(sysUser.getId());
            loginUser.setAccount(sysUser.getAccount());
            loginUser.setEmail(sysUser.getEmail());
            loginUser.setPhoneNumber(sysUser.getPhoneNumber());
            loginUser.setStatus(sysUser.getStatus());
            return loginUser;
        } else {
            return null;
        }
    }

    @Override
    public Set<String> getPermissionUrlsByUserId(Long userId) {
        HashSet<String> permUrls = new HashSet<>();

        //查询用户的所有角色
        List<Long> sysUserRoles = sysRoleService.getUserRoleByUserId(userId);

        //遍历角色,获取所有角色的权限
        for (Long roleId : sysUserRoles) {
            List<SysPermission> sysPermisionsByRole = sysRoleService.getSysPermisionsByRole(roleId);
            for (SysPermission sysPermission : sysPermisionsByRole) {
                String url = sysPermission.getUrl();
                permUrls.add(url);
            }
        }
        return permUrls;
    }
}
