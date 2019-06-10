package com.example.yyw.xmly.mapper;

import com.example.yyw.xmly.modal.xmly.XmlyAlbum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yanzhitao
 * @date 2019/05/07
 **/
@Mapper
public interface IXmlyAlbumMapper extends BaseMapper<XmlyAlbum> {
    /**
     * 按条件查询
     *
     * @param mapParams 参数键值对
     * @return
     */
    List<XmlyAlbum> findByCondition(@Param("mapParams") Map<String, Object> mapParams);

    /**
     * 批量保存
     *
     * @param xmlyAlbumList 专辑集合
     * @return
     */
    int batchSave(@Param("list") List<XmlyAlbum> xmlyAlbumList);

    /**
     * 更新专辑状态
     *
     * @param xmlyAlbum 专辑
     * @return
     */
    int updateStatus(@Param("xmlyAlbum") XmlyAlbum xmlyAlbum);

    /**
     * 上下架
     * @return
     */
    @Update("UPDATE xmly_album SET update_at = #{date},`status` = #{status},modify_date=now() WHERE origin_id = #{id}")
    int upOrLow(@Param("id") Integer id, @Param("date") Date date, @Param("status") int status);
}
