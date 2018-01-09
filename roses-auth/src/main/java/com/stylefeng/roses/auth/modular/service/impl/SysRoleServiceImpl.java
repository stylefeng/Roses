package com.stylefeng.roses.auth.modular.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.roses.auth.modular.entity.SysPermission;
import com.stylefeng.roses.auth.modular.entity.SysRole;
import com.stylefeng.roses.auth.modular.entity.SysUserRole;
import com.stylefeng.roses.auth.modular.mapper.SysRoleMapper;
import com.stylefeng.roses.auth.modular.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户权限吧 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-09
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<Long> getUserRoleByUserId(Long userId) {
        ArrayList<Long> roles = new ArrayList<>();
        List<SysUserRole> sysUserRoles = new SysUserRole().selectList(new EntityWrapper().eq("user_id", userId));
        for (SysUserRole sysUserRole : sysUserRoles) {
            roles.add(sysUserRole.getRoleId());
        }
        return roles;
    }

    /**
     * TODO 编写具体查询
     */
    @Override
    public List<SysPermission> getSysPermisionsByRole(Long roleId) {
        return null;
    }
}
