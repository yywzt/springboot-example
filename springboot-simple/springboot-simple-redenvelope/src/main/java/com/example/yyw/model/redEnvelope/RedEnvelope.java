package com.example.yyw.model.redEnvelope;

import com.example.yyw.enums.EnvelopeEnum;
import com.example.yyw.enums.EnvelopeStatusEnum;
import com.example.yyw.model.GenericModel;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Title red_envelope表的实体类
 * @Description 
 * @version 1.0
 * @Author yanzt
 * @Date 2019-04-23 16:05:32
 */
@Data
public class RedEnvelope extends GenericModel<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  字段名称：类型（私发、群发）
     * 
     * 数据库字段信息：envelope_type INTEGER(10)
     */
    private Integer envelopeType;

    /**
     *  字段名称：发送者id
     * 
     * 数据库字段信息：send_id BIGINT(19)
     */
    private Long sendId;

    /**
     *  字段名称：发送时间
     * 
     * 数据库字段信息：send_date TIMESTAMP(19)
     */
    private Timestamp sendDate;

    /**
     *  字段名称：接收者id(类型为私发)
     * 
     * 数据库字段信息：receive_id BIGINT(19)
     */
    private Long receiveId;

    /**
     *  字段名称：所属群组id(类型为群发)
     * 
     * 数据库字段信息：qun_id BIGINT(19)
     */
    private Long qunId;

    /**
     *  字段名称：备注信息
     * 
     * 数据库字段信息：message VARCHAR(255)
     */
    private String message;

    /**
     *  字段名称：红包总金额
     * 
     * 数据库字段信息：amount DECIMAL(16)
     */
    private BigDecimal amount;

    /**
     *  字段名称：个数
     * 
     * 数据库字段信息：count INTEGER(10)
     */
    private Integer count;

    /**
     *  字段名称：剩余个数
     * 
     * 数据库字段信息：remain_count INTEGER(10)
     */
    private Integer remainCount;

    /**
     *  字段名称：红包状态
     *
     * 数据库字段信息：status INTEGER(2)
     */
    private Integer status;

    @Transient
    private String envelopeTypeName;
    @Transient
    private String envelopeStatusName;

    public String getEnvelopeTypeName(){
        EnvelopeEnum envelopeByCode = EnvelopeEnum.getEnvelopeByCode(getEnvelopeType());
        return envelopeByCode == null?"":envelopeByCode.getValue();
    }

    public String getEnvelopeStatusName(){
        EnvelopeStatusEnum  envelopeStatusEnum= EnvelopeStatusEnum.getEnvelopeStatusByCode(getStatus());
        return envelopeStatusEnum == null?"":envelopeStatusEnum.getValue();
    }


}