package com.example.yyw.model.redEnvelope;

import com.example.yyw.model.GenericModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Title user_qun表的实体类
 * @Description 
 * @version 1.0
 * @Author yanzt
 * @Date 2019-04-23 16:05:32
 */
@Data
public class UserQun extends GenericModel<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  字段名称：用户id
     * 
     * 数据库字段信息：user_id BIGINT(19)
     */
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     *  字段名称：群组id
     * 
     * 数据库字段信息：qun_id BIGINT(19)
     */
    @NotNull(message = "群组id不能为空")
    private Long qunId;

}