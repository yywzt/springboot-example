package com.example.yyw.simple_factory;

import com.example.yyw.simple_factory.enums.SkinTypeEnum;
import com.example.yyw.simple_factory.factorys.SkinFactory;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/24 16:18
 * @describe
 */
public class Main {

    public static void main(String[] args){
        SkinFactory.display(SkinTypeEnum.SPRING_BUTTON.getName());
        SkinFactory.display(SkinTypeEnum.SUMMER_BUTTON.getName());
        SkinFactory.display(SkinTypeEnum.SPRING_TEXTFIELD.getName());
        SkinFactory.display(SkinTypeEnum.SUMMER_TEXTFIELD.getName());
        SkinFactory.display(SkinTypeEnum.SPRING_COMBOBOX.getName());
        SkinFactory.display(SkinTypeEnum.SUMMER_COMBOBOX.getName());
    }
}
