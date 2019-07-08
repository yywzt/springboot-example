package com.example.yyw.model.ssm;

import com.example.yyw.base.GenericModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/13 19:35
 * @describe
 */
@Data
public class Log extends GenericModel<Long> implements Serializable {

    private String groupName;
    private String logMsg;
}
