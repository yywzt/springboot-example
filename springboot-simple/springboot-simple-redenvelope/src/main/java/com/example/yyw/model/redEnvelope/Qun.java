package com.example.yyw.model.redEnvelope;

import com.example.yyw.model.GenericModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Title qun表的实体类
 * @Description 
 * @version 1.0
 * @Author yanzt
 * @Date 2019-04-23 16:05:32
 */
@Data
public class Qun extends GenericModel<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  字段名称：群组名称
     * 
     * 数据库字段信息：qun_name VARCHAR(255)
     */
    @NotBlank(message = "群组名称不能为空")
    private String qunName;

}