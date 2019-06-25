package com.example.yyw.model.ssm;

import com.example.yyw.model.base.GenericModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/6/25 17:59
 * @describe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestTransaction extends GenericModel<Long> {

    private String testTransactionName;
    private String testTransactionMsg;

}
