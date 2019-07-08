package com.example.yyw.jpa.modal;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/8 11:49
 * @describe
 */
@Data
@Entity
@Table(name = "class")
public class Class extends BaseModal<Long> {

    @Column(length = 90)
    private String className;

    @Column(length = 90)
    private String classNo;

    @Column(precision = 12, scale = 3)
    private BigDecimal price;
    @Column(precision = 10, scale = 2)
    private BigDecimal price2;
}
