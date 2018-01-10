package com.stylefeng.roses.auth.modular.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.roses.auth.modular.entity.SysPermission;
import com.stylefeng.roses.auth.modular.entity.SysRole;

import java.util.List;

/**
 * <p>
  * 用户权限吧 Mapper 接口
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-09
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    
    /**
     * 通过角色id获取权限
     */
    List<SysPermission> getPermissionsByRoleId(Long roleId);

}