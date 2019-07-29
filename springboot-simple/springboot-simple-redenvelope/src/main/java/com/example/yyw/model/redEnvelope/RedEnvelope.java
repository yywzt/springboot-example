package com.example.yyw.model.redEnvelope;

import com.example.yyw.base.GenericModel;
import com.example.yyw.enums.EnvelopeEnum;
import com.example.yyw.enums.EnvelopeStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @version 1.0
 * @Title red_envelope表的实体类
 * @Description
 * @Author yanzt
 * @Date 2019-04-23 16:05:32
 */
@Data
public class RedEnvelope extends GenericModel<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段名称：类型（私发、群发）
     * <p>
     * 数据库字段信息：envelope_type INTEGER(10)
     */
    private Integer envelopeType;

    /**
     * 字段名称：发送者id
     * <p>
     * 数据库字段信息：send_id BIGINT(19)
     */
    private Long sendId;

    /**
     * 字段名称：发送时间
     * <p>
     * 数据库字段信息：send_date TIMESTAMP(19)
     */
    private LocalDateTime sendDate;

    /**
     * 字段名称：接收者id(类型为私发)
     * <p>
     * 数据库字段信息：receive_id BIGINT(19)
     */
    private Long receiveId;

    /**
     * 字段名称：所属群组id(类型为群发)
     * <p>
     * 数据库字段信息：qun_id BIGINT(19)
     */
    private Long qunId;

    /**
     * 字段名称：备注信息
     * <p>
     * 数据库字段信息：message VARCHAR(255)
     */
    private String message;

    /**
     * 字段名称：单个红包金额
     * <p>
     * 数据库字段信息：amount DECIMAL(16)
     */
    private BigDecimal amount;

    /**
     * 字段名称：个数
     * <p>
     * 数据库字段信息：count INTEGER(10)
     */
    private Integer count;

    /**
     * 字段名称：剩余个数
     * <p>
     * 数据库字段信息：remain_count INTEGER(10)
     */
    private Integer remainCount;

    /**
     * 字段名称：红包状态
     * <p>
     * 数据库字段信息：status INTEGER(2)
     */
    private Integer status;

    public String getEnvelopeTypeName() {
        EnvelopeEnum envelopeByCode = EnvelopeEnum.getEnvelopeByCode(getEnvelopeType());
        return envelopeByCode == null ? "" : envelopeByCode.getValue();
    }

    public String getEnvelopeStatusName() {
        EnvelopeStatusEnum envelopeStatusEnum = EnvelopeStatusEnum.getEnvelopeStatusByCode(getStatus());
        return envelopeStatusEnum == null ? "" : envelopeStatusEnum.getValue();
    }


}