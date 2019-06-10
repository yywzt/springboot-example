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
     * 按条件查询
     *
     * @param mapParams
     * @return
     */
    List<XmlyTrack> findByCondition(@Param("mapParams") Map<String, Object> mapParams);

    /**
     * 批量保存
     *
     * @param xmlyTrackList
     * @return
     */
    int batchSave(@Param("list") List<XmlyTrack> xmlyTrackList);

    @Update("UPDATE xmly_track SET update_at = ?2,`status` = ?3 WHERE id = ?1")
    int ipOrLow(Integer id, Date date, int status);
}
