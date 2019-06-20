package com.example.yyw.xmly.mapper;

import com.example.yyw.xmly.enums.StatusEnum;
import com.example.yyw.xmly.modal.xmly.XmlyTrack;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yanzhitao
 * @date 2019/05/07
 **/
@Mapper
public interface IXmlyTrackMapper extends BaseMapper<XmlyTrack> {
    /**
     * 批量保存
     *
     * @param xmlyTrackList
     * @return
     */
    int batchSave(@Param("list") List<XmlyTrack> xmlyTrackList);

    List<XmlyTrack> findByCondition(@Param("mapParams") Map<String, Object> mapParams);

    List<XmlyTrack> findXmlyTrackByConditionLimit(@Param("mapParams") Map<String, Object> mapParams);

    List<XmlyTrack> findOriginIdAndExtendCategoryOriginIdByCondition(@Param("mapParams") Map<String, Object> mapParams);

    List<String> findExtendCategoryOriginIdByCondition(@Param("mapParams") Map<String, Object> mapParams);

    @Update("UPDATE xmly_track SET update_at = #{date},`status` = #{status},modify_date=now() WHERE origin_id = #{id}")
    int ipOrLow(@Param("id") Integer id, @Param("date") Date date, @Param("status") int status);

    int batchIpOrLow(@Param("ids") List<Long> ids, @Param("date") Date date, @Param("status") int status);
}
