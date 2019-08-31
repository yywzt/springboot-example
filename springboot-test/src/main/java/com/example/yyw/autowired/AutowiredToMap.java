package com.example.yyw.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/31 15:13
 */
@Component
public class AutowiredToMap {

    @Autowired
    private Map<String, BeanInterface> beanInterfaceMap;


    public Map<String, BeanInterface> getMap(){
        return beanInterfaceMap;
    }
}
