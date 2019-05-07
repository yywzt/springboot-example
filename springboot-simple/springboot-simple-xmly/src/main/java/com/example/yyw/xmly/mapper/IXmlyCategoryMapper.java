package com.example.yyw.xmly.mapper;

import com.example.yyw.xmly.modal.xmly.XmlyCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/6 17:39
 * @describe
 */
@Mapper
public interface IXmlyCategoryMapper extends BaseMapper<XmlyCategory> {

    void batchSave(@Param("list") List<XmlyCategory> xmlyCategoryList);

    void updateStatus(@Param("xmlyCategory") XmlyCategory xmlyCategory);

    List<XmlyCategory> findByCondition(@Param("mapParams") Map<String, Object> mapParams);
}
