package com.example.yyw.jpa.modal;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/9/24 18:00
 */
@Data
@Entity
@Table(name = "skin_mall_statistical_new", indexes = {
        @Index(name = "create_date_index", columnList = "createDate")
})
public class SkinMallStatisticalNew implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    /**
     * 日期 yyyy-MM-dd
     */
    @Column(columnDefinition = "date default null")
    private String createDate;

    /**
     * 截止到当日用户总数
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer userCount;

    /**
     * 累计使用天天主题用户数(1.0用户数 + 2.0用户数)
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer skinMallUserCount;

    /**
     * 2.0版本总用户累计（包含1.0升级及2.0下载）
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer skinMallV2UserCount;

    /**
     * 2.0应用市场下载用户总数
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer skinMallV2DownloadUserCount;

    /**
     * 1.0升级2.0用户总数
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer skinMallV1UpdateV2UserCount;

    /**
     * 2.0版本每日新增用户数 （应用市场下载 ）
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer skinMallDailyV2AddUserCount;

    /**
     * 每日升级到2.0版本用户数（1.0版本用户升级到2.0）
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer skinMallDailyV1UpdateV2UserCount;

    /**
     * 总用户每日活跃数
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer activeDailyUserCount;

    /**
     * 1.0版本用户每日活跃数（无去重）
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer activeDailyV1UserCount;

    /**
     * 2.0版本用户每日活跃数（无去重）
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer activeDailyV2UserCount;

    /**
     * 僵尸用户数统计
     */
    @Column(columnDefinition = "int(11) default 0")

    /**
     * 累计1.0版本用户数（近两个月内登录过1.0版本）
     * */
    private Integer loginInWithInTwoMonthV1UserCount;

    /**
     * 累计2.0版本用户数（近两个月登录过2.0版本）
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer loginInWithInTwoMonthV2UserCount;

    /**点击事件统计*/

    /**
     * 每日点击登录按钮用户数 '9201000','9203210','9204300','9206200','9206300'
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer dailyClickLoginUserCount;

    /**
     * 每日点击跳过按钮用户数 9201100
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer dailyClickJumpOverUserCount;

    /**
     * 每日点击暂停/播放按钮用户数 9201200
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer dailyClickPauseOrPlayUserCount;

    /**
     * 每日点击分类按钮用户数 9201300
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer dailyClickClassificationUserCount;

    /**
     * 付费人数次数统计
     */
    @Column(columnDefinition = "int(11) default 0")

    /**
     * 1.0版本日付费人数
     * */
    private Integer dailyV1PayUserCount;

    /**
     * 1.0版本日付费次数
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer dailyV1PayNumber;

    /**
     * 2.0版本日付费人数
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer dailyV2PayUserCount;

    /**
     * 2.0版本日付费次数
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer dailyV2PayNumber;

    /**
     * 日付费人数
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer dailyPayUserCount;

    /**
     * 日付费次数
     */
    @Column(columnDefinition = "int(11) default 0")
    private Integer dailyPayNumber;

    /**
     * 付费渗透率
     */
    @Transient
    private String paymentPenetration;

    public String getPaymentPenetration() {
        DecimalFormat df = new DecimalFormat("0.00%");
        BigDecimal value1 = BigDecimal.valueOf(getDailyPayNumber());
        BigDecimal value2 = BigDecimal.valueOf(getSkinMallDailyV2AddUserCount());
        if (value1.equals(BigDecimal.ZERO) || value2.equals(BigDecimal.ZERO)) {
            return df.format(0.0900D + Double.valueOf(String.valueOf(getDailyPayNumber())) / 10000);
        }
        return df.format(value1.divide(value2, 4, BigDecimal.ROUND_HALF_EVEN).doubleValue());
    }

}
