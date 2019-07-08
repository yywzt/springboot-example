package com.example.yyw.model.ssm;

import com.example.yyw.base.GenericModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ywyw2424@foxmail.com
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
