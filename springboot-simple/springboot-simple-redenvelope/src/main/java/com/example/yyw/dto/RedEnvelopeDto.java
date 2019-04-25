package com.example.yyw.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/4/23 16:35
 * @describe 红包信息参数传递
 */
@Data
public class RedEnvelopeDto implements Serializable {

    /**
     * 红包类型
     * @see com.example.yyw.enums.EnvelopeEnum
     * */
    @NotNull(message = "红包类型不能为空")
    private Integer envelopeType;

    @NotNull(message = "发送人不能为空")
    private Long sendId;

    /**
     * 群发时没有意义
     * */
    private Long receiveId;

    /**
     * 私发时没有意义
     * */
    private Long qunId;

    private String message;

    @NotNull(message = "金额不能为空")
    private BigDecimal amount;

    /**
     * 私发时没有意义
     * */
    private Integer count;

}
