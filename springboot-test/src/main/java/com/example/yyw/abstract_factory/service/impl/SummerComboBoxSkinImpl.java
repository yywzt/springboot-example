package com.example.yyw.abstract_factory.service.impl;

import com.example.yyw.abstract_factory.service.SkinService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/24 15:44
 * @describe
 */
@Slf4j
public class SummerComboBoxSkinImpl implements SkinService {
    @Override
    public String create() {
        return "SummerComboBoxSkinImpl create";
    }
}
