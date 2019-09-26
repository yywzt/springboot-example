package com.example.yyw.jpa.modal;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/9/25 12:14
 */
@Data
@Entity
@Table(name = "skin_mall_statistical_daily_active_user", indexes = {
        @Index(name = "create_date_index",columnList = "createDate")
})
public class SkinMallStatisticalDailyActiveUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    /**
     * 渠道
     */
    @Column(columnDefinition = "varchar(60) default null")
    private String channelId;

    /**
     * 日期 yyyy-MM-dd
     */
    @Column(columnDefinition = "date default null")
    private Date createDate;

    /**
     * 用户ID
     * */
    @Column(columnDefinition = "bigint(20) default null")
    private Long userId;

    /**
     * 版本号
     * */
    @Column(columnDefinition = "varchar(60) default null")
    private String versionCode;
}
