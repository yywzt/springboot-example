package com.example.yyw.xmly.mapper;

import com.example.yyw.xmly.modal.xmly.XmlyAlbum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author buxianglong
 * @date 2018/12/04
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
}
