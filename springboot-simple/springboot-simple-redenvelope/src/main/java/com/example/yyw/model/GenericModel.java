package com.example.yyw.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by yanzhitao on 2018/7/16.
 *
 * @JsonIgnore 在json序列化时将java bean中的一些属性忽略掉，序列化和反序列化都受影响。
 * @Transient 指定字段不在表中
 */
@Data
public class GenericModel<PK> implements Serializable {
    private static final long serialVersionUID = 1L;

    protected PK id;

    /**
     * 创建人
     */
    protected String createdBy;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Timestamp creationDate;

    /**
     * 修改人
     */
    protected String updatedBy;

    /**
     * 修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    protected Timestamp updationDate;

    /**
     * 是否可用
     */
    protected Long enabledFlag = 1L;

}