package com.example.yyw.abstract_factory.factorys.impl;

import com.example.yyw.abstract_factory.enums.SkinTypeEnum;
import com.example.yyw.abstract_factory.factorys.AbstractSkinFactory;
import com.example.yyw.abstract_factory.service.SkinService;
import com.example.yyw.abstract_factory.service.impl.SpringButtonSkinImpl;
import com.example.yyw.abstract_factory.service.impl.SpringComboBoxSkinImpl;
import com.example.yyw.abstract_factory.service.impl.SpringTextFieldSkinImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/24 17:08
 * @describe
 */
@Slf4j
public class SpringSkinFactory extends AbstractSkinFactory {

    private final SkinService springInput = () -> "spring_input create";

    private Map<String, SkinService> skinMap = new HashMap<String, SkinService>() {{
        put(SkinTypeEnum.SPRING_BUTTON.getName(), new SpringButtonSkinImpl());
        put(SkinTypeEnum.SPRING_TEXTFIELD.getName(), new SpringTextFieldSkinImpl());
        put(SkinTypeEnum.SPRING_COMBOBOX.getName(), new SpringComboBoxSkinImpl());
        put(SkinTypeEnum.SPRING_INPUT.getName(), springInput);
    }};

    @Override
    public Map<String, SkinService> getMap() {
        return skinMap;
    }
}
