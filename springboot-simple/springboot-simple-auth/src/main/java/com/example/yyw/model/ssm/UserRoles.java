package com.example.yyw.model.ssm;

import com.example.yyw.base.GenericModel;

import java.io.Serializable;

/**
 * @Title user_roles表的实体类
 * @Description 
 * @version 1.0
 * @Author yanzt
 * @Date 2018-07-16 14:39:48
 */
public class UserRoles extends GenericModel<Long> implements Serializable {
    /**
     *  字段名称：
     * 
     * 数据库字段信息：user_id INTEGER(10)
     */
    private Integer userId;

    /**
     *  字段名称：
     * 
     * 数据库字段信息：role_id INTEGER(10)
     */
    private Integer roleId;

    /**
     * user_roles
     *
     * @mbg.generated 2018-07-16 14:39:48
     */
    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @describe
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", roleId=").append(roleId);
        sb.append("]");
        return sb.toString();
    }
}