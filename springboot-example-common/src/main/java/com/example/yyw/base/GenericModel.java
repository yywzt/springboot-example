package com.example.yyw.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected LocalDateTime creationDate;

    /**
     * 修改人
     */
    protected String updatedBy;

    /**
     * 修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    protected LocalDateTime updationDate;

    /**
     * 是否可用
     */
    protected Long enabledFlag = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericModel<?> that = (GenericModel<?>) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(updatedBy, that.updatedBy) &&
                Objects.equals(updationDate, that.updationDate) &&
                Objects.equals(enabledFlag, that.enabledFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdBy, creationDate, updatedBy, updationDate, enabledFlag);
    }
}