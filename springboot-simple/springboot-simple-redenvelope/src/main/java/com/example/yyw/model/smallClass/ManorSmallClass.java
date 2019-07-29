package com.example.yyw.model.smallClass;

import com.example.yyw.base.GenericModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/29 11:45
 * @describe
 */
@Data
public class ManorSmallClass extends GenericModel<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String question;
    private String answerOption;
    private String answer;
}
