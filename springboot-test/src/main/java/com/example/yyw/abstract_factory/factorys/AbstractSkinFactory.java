package com.example.yyw.abstract_factory.factorys;

import com.example.yyw.abstract_factory.service.SkinService;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/24 17:06
 * @describe
 */
@Slf4j
public abstract class AbstractSkinFactory {

    public String handle(String type) {
        SkinService skinService = getMap().get(type);
        if (skinService == null) {
            log.error("错误的类型：{}", type);
        }
        skinService.create();
        return "";
    }

    public abstract Map<String, SkinService> getMap();
}
