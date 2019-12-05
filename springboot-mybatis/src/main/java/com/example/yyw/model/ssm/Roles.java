package com.example.yyw.model.ssm;

import com.example.yyw.base.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Title roles表的实体类
 * @Description 
 * @version 1.0
 * @Author yanzt
 * @Date 2018-07-16 20:23:14
 */
@Data
public class Roles extends GenericModel<Long> implements Serializable {
    /**
     *  字段名称：角色名称
     * 
     * 数据库字段信息：role_name VARCHAR(30)
     */
    private String roleName;

    /**
     *  字段名称：角色编码
     * 
     * 数据库字段信息：role_code VARCHAR(30)
     */
    private String roleCode;

    /**
     * roles
     *
     * @mbg.generated 2018-07-16 20:23:14
     */
    private static final long serialVersionUID = 1L;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
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
        sb.append(", roleName=").append(roleName);
        sb.append(", roleCode=").append(roleCode);
        sb.append("]");
        return sb.toString();
    }
}