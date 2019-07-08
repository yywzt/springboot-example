package com.example.yyw.jpa.modal;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/8 11:36
 * @describe
 */
@Data
@Entity
@Table(name = "student")
public class Student extends BaseModal<Long> {

    @Column(length = 90)
    private String name;

    @Column(length = 1)
    private String sex;

    @Column(length = 3)
    private String age;

    @Column(length = 60)
    private String sno;

    @Column(name = "eating_time", nullable = false, columnDefinition = "datetime(3)")
    private LocalDateTime eatingTime;
}
