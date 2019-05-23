package com.example.yyw.xmly.mapper;

import com.example.yyw.xmly.modal.xmly.XmlyAlbum;
import com.example.yyw.xmly.modal.xmly.XmlyCategory;
import com.example.yyw.xmly.modal.xmly.XmlyTrack;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/20 16:16
 * @describe
 */
@Mapper
public interface BasedMapper {

    /**
     * 重命名表
     * @param sourceTableName
     * @param newTableName
     */
    void alterTableName(@Param("sourceTableName") String sourceTableName, @Param("newTableName") String newTableName);

    /**
     * 删除表
     * @param tableName
     */
    void dropTable(@Param("tableName") String tableName);

    /**
     * 创建表
     */
    void createTable(@Param("newTableName") String newTableName, @Param("sourceTableName") String sourceTableName);

    int batchSaveCategorys(@Param("list") List<XmlyCategory> xmlyCategoryList, @Param("tableName") String tableName);

    int batchSaveAlbums(@Param("list") List<XmlyAlbum> xmlyAlbumList, @Param("tableName") String tableName);

    int batchSaveTracks(@Param("list") List<XmlyTrack> xmlyTrackList, @Param("tableName") String tableName);

}
