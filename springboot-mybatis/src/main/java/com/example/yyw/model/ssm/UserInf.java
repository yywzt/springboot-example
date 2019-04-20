package com.example.yyw.model.ssm;

import com.example.yyw.model.base.GenericModel;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Title user_inf表的实体类
 * @Description 
 * @version 1.0
 * @Author yanzt
 * @Date 2018-07-16 14:49:45
 */
public class UserInf extends GenericModel<Long> implements Serializable {
    /**
     *  字段名称：
     * 
     * 数据库字段信息：uname VARCHAR(255)
     */
    @NotBlank(message = "用户名不能为空")
    private String uname;

    /**
     *  字段名称：
     * 
     * 数据库字段信息：passwd VARCHAR(255)
     */
    @NotBlank(message = "密码不能为空")
    private String passwd;

    /**
     *  字段名称：
     * 
     * 数据库字段信息：gentel VARCHAR(255)
     */
    private String gentel;

    /**
     *  字段名称：
     * 
     * 数据库字段信息：email VARCHAR(255)
     */
    private String email;

    /**
     *  字段名称：
     * 
     * 数据库字段信息：city VARCHAR(255)
     */
    private String city;

    /**
     * user_inf
     *
     * @mbg.generated 2018-07-16 14:49:45
     */
    private static final long serialVersionUID = 1L;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getGentel() {
        return gentel;
    }

    public void setGentel(String gentel) {
        this.gentel = gentel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "UserInf{" +
                "uname='" + uname + '\'' +
                ", passwd='" + passwd + '\'' +
                ", gentel='" + gentel + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", roles=" + roles +
                ", id=" + id +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                ", updatedBy='" + updatedBy + '\'' +
                ", updationDate=" + updationDate +
                ", enabledFlag=" + enabledFlag +
                '}';
    }

    /**
     * @describe
     * @return String
     */

    private Set<Roles> roles;

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}