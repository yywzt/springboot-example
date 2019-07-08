package com.example.yyw.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/30 17:26
 * @describe
 */
@Data
public class json {

    @JsonIgnore
    private String name;
    private String gender;
    private Integer age;
}
