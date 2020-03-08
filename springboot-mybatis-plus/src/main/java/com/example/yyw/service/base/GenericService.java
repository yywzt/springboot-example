package com.example.yyw.service.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/6/26 22:50
 * @describe
 */
@Slf4j
public abstract class GenericService<T> extends ServiceImpl<BaseMapper<T>,T> {
}
