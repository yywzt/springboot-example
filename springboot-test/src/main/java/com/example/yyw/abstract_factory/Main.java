package com.example.yyw.abstract_factory;

import com.example.yyw.abstract_factory.enums.SkinFactoryTypeEnum;
import com.example.yyw.abstract_factory.enums.SkinTypeEnum;
import com.example.yyw.abstract_factory.factorys.AbstractSkinFactory;
import com.example.yyw.abstract_factory.factorys.impl.SpringSkinFactory;
import com.example.yyw.abstract_factory.factorys.impl.SummerSkinFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/24 17:27
 * @describe
 */
@Slf4j
public class Main {

    private static Map<String, AbstractSkinFactory> skinFactoryMap = new HashMap<String, AbstractSkinFactory>() {{
        put(SkinFactoryTypeEnum.SPRING.getName(), new SpringSkinFactory());
        put(SkinFactoryTypeEnum.SUMMER.getName(), new SummerSkinFactory());
    }};

    public static void main(String[] args) {
        log.info("{}", skinFactoryMap.get(SkinFactoryTypeEnum.SPRING.getName()).handle(SkinTypeEnum.SPRING_BUTTON.getName()));
        log.info("{}", skinFactoryMap.get(SkinFactoryTypeEnum.SPRING.getName()).handle(SkinTypeEnum.SPRING_TEXTFIELD.getName()));
        log.info("{}", skinFactoryMap.get(SkinFactoryTypeEnum.SPRING.getName()).handle(SkinTypeEnum.SPRING_COMBOBOX.getName()));
        log.info("{}", skinFactoryMap.get(SkinFactoryTypeEnum.SPRING.getName()).handle(SkinTypeEnum.SPRING_INPUT.getName()));
        log.info("{}", skinFactoryMap.get(SkinFactoryTypeEnum.SUMMER.getName()).handle(SkinTypeEnum.SUMMER_BUTTON.getName()));
        log.info("{}", skinFactoryMap.get(SkinFactoryTypeEnum.SUMMER.getName()).handle(SkinTypeEnum.SUMMER_TEXTFIELD.getName()));
        log.info("{}", skinFactoryMap.get(SkinFactoryTypeEnum.SUMMER.getName()).handle(SkinTypeEnum.SUMMER_COMBOBOX.getName()));
    }
}
