package com.example.yyw.model.base;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by yanzhitao on 2018/7/16.
 *
 * @JsonIgnore 在json序列化时将java bean中的一些属性忽略掉，序列化和反序列化都受影响。
 * @Transient 指定字段不在表中
 */
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Timestamp creationDate;

    /**
     * 修改人
     */
    protected String updatedBy;

    /**
     * 修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    protected Timestamp updationDate;

    /**
     * 是否可用
     */
    protected Long enabledFlag = 1L;


    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdationDate() {
        return updationDate;
    }

    public void setUpdationDate(Timestamp updationDate) {
        this.updationDate = updationDate;
    }

    public Long getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Long enabledFlag) {
        this.enabledFlag = enabledFlag;
    }


    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("id='").append(getId()).append("'");
        buffer.append("]");

        return buffer.toString();
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof GenericModel)) {
            return false;
        }

        GenericModel other = (GenericModel) o;
        if (getId() != null && other.getId() != null) {
            if (getId() instanceof Comparable) {
                return ((Comparable) getId()).compareTo(other.getId()) == 0;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int hashCode() {

        int result = 17;

        if (getId() instanceof Comparable) {
            result = getId().hashCode();
        }

        return result;
    }
}