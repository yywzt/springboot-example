package com.example.yyw.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/12/5 10:37
 * @description
 */
@Slf4j
public class BeanMapUtil {

    public static Map beanConvertToMap(Object bean) {
        HashMap<String, Object> map = Maps.newHashMap();
        try {
            Class beanClass = bean.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
            List<PropertyDescriptor> propertyDescriptorList = Arrays.stream(beanInfo.getPropertyDescriptors())
                    .filter(propertyDescriptor -> !("class").equals(propertyDescriptor.getName()))
                    .collect(Collectors.toList());
            for (PropertyDescriptor propertyDescriptor : propertyDescriptorList) {
                map.put(propertyDescriptor.getName(), propertyDescriptor.getReadMethod().invoke(bean, new Object[0]));
            }
        } catch (Exception e) {
            log.error("beanConvertToMap error: {}", e);
        }
        return map;
    }
}
