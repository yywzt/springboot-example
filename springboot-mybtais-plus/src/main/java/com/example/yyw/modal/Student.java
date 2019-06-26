package com.example.yyw.modal;

import com.example.yyw.base.GenericModel;
import lombok.Data;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/6/26 22:23
 * @describe
 */
@Data
public class Student extends GenericModel<Long> {

    private String name;
    private String gender;
    private Integer age;
}
