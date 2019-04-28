package com.example.yyw.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by yanzhitao on 2018/7/16.
 *
 * @JsonIgnore 在json序列化时将java bean中的一些属性忽略掉，序列化和反序列化都受影响。
 * @Transient 指定字段不在表中
 */
@Data
public class GenericModel<PK> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected PK id;

    /**
     * 创建人
     */
    protected String createdBy;

    /**
     * 创建日期
     */
    protected LocalDateTime creationDate;

    /**
     * 修改人
     */
    protected String updatedBy;

    /**
     * 修改日期
     */
    protected LocalDateTime updationDate;

    /**
     * 是否可用
     */
    protected Long enabledFlag = 1L;

}