package com.example.yyw.mapper;

import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/11/6 23:41
 * @description
 */
@Mapper
public interface HotelPolicyMapper extends BaseMapper<HotelPolicy>, MySqlMapper<HotelPolicy> {
}
