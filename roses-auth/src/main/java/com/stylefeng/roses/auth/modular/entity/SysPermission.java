package com.stylefeng.roses.auth.modular.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-09
 */
@TableName("sys_permission")
public class SysPermission extends Model<SysPermission> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 资源名称
     */
	private String name;
    /**
     * 父级id
     */
	private Long pid;
    /**
     * 详情
     */
	private String description;
    /**
     * 菜单地址
     */
	private String url;
    /**
     * 是否是菜单: Y-是菜单  N-不是菜单
     */
	@TableField("menu_flag")
	private String menuFlag;
    /**
     * 图标
     */
	private String icon;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 权限编号
     */
	private String code;
    /**
     * 状态: 1-启用 0-禁用
     */
	private Integer status;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenuFlag() {
		return menuFlag;
	}

	public void setMenuFlag(String menuFlag) {
		this.menuFlag = menuFlag;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysPermission{" +
			"id=" + id +
			", name=" + name +
			", pid=" + pid +
			", description=" + description +
			", url=" + url +
			", menuFlag=" + menuFlag +
			", icon=" + icon +
			", sort=" + sort +
			", code=" + code +
			", status=" + status +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
