package com.example.yyw.simple_factory.factorys;

import com.example.yyw.simple_factory.enums.SkinTypeEnum;
import com.example.yyw.simple_factory.service.*;
import com.example.yyw.simple_factory.service.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/24 15:51
 * @describe
 */
public class SkinFactory {

    /*private static Map<String, Button> buttonMap = new HashMap<String, Button>(){{
       put(SkinTypeEnum.SPRING_BUTTON.getName(), new SpringButtonSkinImpl());
       put(SkinTypeEnum.SUMMER_BUTTON.getName(), new SummerButtonSkinImpl());
    }};*/
    private static Map<String, SkinService> buttonMap = new HashMap<String, SkinService>(){{
       put(SkinTypeEnum.SPRING_BUTTON.getName(), new SpringButtonSkinImpl());
       put(SkinTypeEnum.SUMMER_BUTTON.getName(), new SummerButtonSkinImpl());
       put(SkinTypeEnum.SPRING_TEXTFIELD.getName(), new SpringTextFieldSkinImpl());
       put(SkinTypeEnum.SUMMER_TEXTFIELD.getName(), new SummerTextFieldSkinImpl());
       put(SkinTypeEnum.SPRING_COMBOBOX.getName(), new SpringComboBoxSkinImpl());
       put(SkinTypeEnum.SUMMER_COMBOBOX.getName(), new SummerComboBoxSkinImpl());
    }};

    public static void display(String type){
        buttonMap.get(type).create();
    }
}
