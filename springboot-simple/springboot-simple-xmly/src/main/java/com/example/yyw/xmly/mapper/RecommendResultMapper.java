package com.example.yyw.xmly.mapper;

import com.example.yyw.xmly.modal.xmly.RecommendResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author buxianglong
 * @date 2018/12/03
 **/
@Mapper
public interface RecommendResultMapper extends BaseMapper<RecommendResult> {
    List<RecommendResult> findByCondition(@Param("mapParams") Map<String, Object> paramMap);

    int batchSave(@Param("list") List<RecommendResult> recommendResultList);

    int updateStatusByItemId(@Param("mapParams") Map<String, Object> paramMap);
}
