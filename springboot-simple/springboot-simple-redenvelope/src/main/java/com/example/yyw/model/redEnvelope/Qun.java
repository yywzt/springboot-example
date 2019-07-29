package com.example.yyw.model.redEnvelope;

import com.example.yyw.base.GenericModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @version 1.0
 * @Title qun表的实体类
 * @Description
 * @Author yanzt
 * @Date 2019-04-23 16:05:32
 */
@Data
public class Qun extends GenericModel<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段名称：群组名称
     * <p>
     * 数据库字段信息：qun_name VARCHAR(255)
     */
    @NotBlank(message = "群组名称不能为空")
    private String qunName;

}