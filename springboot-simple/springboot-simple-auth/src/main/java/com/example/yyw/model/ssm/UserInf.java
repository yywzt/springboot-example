package com.example.yyw.model.ssm;

import com.example.yyw.base.GenericModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

/**
 * @Title user_inf表的实体类
 * @Description 
 * @version 1.0
 * @Author yanzt
 * @Date 2018-07-16 14:49:45
 */
@Data
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

    /**
     * @describe
     * @return String
     */

    private Set<String> roles;

    private Set<String> permission;

}