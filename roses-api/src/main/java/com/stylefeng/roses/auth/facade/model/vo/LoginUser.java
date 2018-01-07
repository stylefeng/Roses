package com.stylefeng.roses.auth.facade.model.vo;

/**
 * 登录中的用户信息
 *
 * @author fengshuonan
 * @date 2017-11-09-下午5:47
 */
public class LoginUser {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 部门id
     */
    private Integer deptId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
