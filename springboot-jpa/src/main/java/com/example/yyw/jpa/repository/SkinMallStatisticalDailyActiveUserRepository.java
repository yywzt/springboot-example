package com.example.yyw.jpa.repository;

import com.example.yyw.jpa.modal.SkinMallStatisticalDailyActiveUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;


/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/9/25 14:26
 */
public interface SkinMallStatisticalDailyActiveUserRepository extends JpaRepository<SkinMallStatisticalDailyActiveUser, Long> {

    @Query("select max(createDate) from SkinMallStatisticalDailyActiveUser where channelId=?1")
    Date findMaxCreateDateByChannelId(String channelId);

    List<SkinMallStatisticalDailyActiveUser> findAllByChannelIdAndCreateDateBetween(String channelId, String start, String end);

    @Query(value = "select * from skin_mall_statistical_daily_active_user where channel_id=?1 and create_date between ?2 and ?3", nativeQuery = true)
    List<SkinMallStatisticalDailyActiveUser> findAllByChannelIdAndCreateDateBetweenNative(String channelId, String start, String end);
}
