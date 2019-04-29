package com.example.yyw.model.redEnvelope;

import com.example.yyw.model.GenericModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @version 1.0
 * @Title user表的实体类
 * @Description
 * @Author yanzt
 * @Date 2019-04-23 16:05:32
 */
@Data
public class User extends GenericModel<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段名称：用户名
     * <p>
     * 数据库字段信息：user_name VARCHAR(255)
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 字段名称：真实姓名
     * <p>
     * 数据库字段信息：real_name VARCHAR(255)
     */
    private String realName;

    /**
     * 字段名称：余额
     * <p>
     * 数据库字段信息：money DECIMAL(16)
     */
    private BigDecimal money;

}