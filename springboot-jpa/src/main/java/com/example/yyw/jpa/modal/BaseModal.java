package com.example.yyw.jpa.modal;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/8 11:38
 * @describe
 */
@Data
@MappedSuperclass
public class BaseModal<PK> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected PK id;

    /**
     * 创建人
     */
    @Column(name = "created_by",length = 60)
    protected String createdBy;

    /**
     * 创建日期
     */
    @Column(name = "creation_date",columnDefinition = "timestamp(3) NULL DEFAULT NULL")
    protected LocalDateTime creationDate;

    /**
     * 修改人
     */
    @Column(name = "updated_by",length = 60)
    protected String updatedBy;

    /**
     * 修改日期
     */
    @Column(name = "updation_date",columnDefinition = "timestamp(3) NULL DEFAULT NULL")
    protected LocalDateTime updationDate;

    /**
     * 是否可用
     */
    @Column(name = "enabled_flag",columnDefinition = "tinyint(4) DEFAULT '1'")
    protected Long enabledFlag = 1L;
}
