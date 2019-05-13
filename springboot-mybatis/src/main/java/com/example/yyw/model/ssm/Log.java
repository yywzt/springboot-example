package com.example.yyw.model.ssm;

import com.example.yyw.model.base.GenericModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/13 19:35
 * @describe
 */
@Data
public class Log extends GenericModel<Long> implements Serializable {

    private String groupName;
    private String logMsg;
}
