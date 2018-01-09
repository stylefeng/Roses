package com.stylefeng.roses.auth.modular.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 权限-资源中间表
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-09
 */
@TableName("sys_role_perm")
public class SysRolePerm extends Model<SysRolePerm> {

    private static final long serialVersionUID = 1L;

    @TableId("role_id")
	private Long roleId;
	@TableField("perm_id")
	private Long permId;


	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermId() {
		return permId;
	}

	public void setPermId(Long permId) {
		this.permId = permId;
	}

	@Override
	protected Serializable pkVal() {
		return this.roleId;
	}

	@Override
	public String toString() {
		return "SysRolePerm{" +
			"roleId=" + roleId +
			", permId=" + permId +
			"}";
	}
}
