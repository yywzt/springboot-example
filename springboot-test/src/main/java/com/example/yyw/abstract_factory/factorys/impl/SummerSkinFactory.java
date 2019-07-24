package com.example.yyw.abstract_factory.factorys.impl;

import com.example.yyw.abstract_factory.enums.SkinTypeEnum;
import com.example.yyw.abstract_factory.factorys.AbstractSkinFactory;
import com.example.yyw.abstract_factory.service.SkinService;
import com.example.yyw.abstract_factory.service.impl.SummerButtonSkinImpl;
import com.example.yyw.abstract_factory.service.impl.SummerComboBoxSkinImpl;
import com.example.yyw.abstract_factory.service.impl.SummerTextFieldSkinImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/24 17:09
 * @describe
 */
public class SummerSkinFactory extends AbstractSkinFactory {
    private Map<String, SkinService> skinMap = new HashMap<String, SkinService>() {{
        put(SkinTypeEnum.SUMMER_BUTTON.getName(), new SummerButtonSkinImpl());
        put(SkinTypeEnum.SUMMER_TEXTFIELD.getName(), new SummerTextFieldSkinImpl());
        put(SkinTypeEnum.SUMMER_COMBOBOX.getName(), new SummerComboBoxSkinImpl());
    }};

    @Override
    public Map<String, SkinService> getMap() {
        return skinMap;
    }
}
