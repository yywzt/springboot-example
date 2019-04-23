package com.example.yyw.model.redEnvelope;

import com.example.yyw.model.GenericModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title red_envelope_detail表的实体类
 * @Description 
 * @version 1.0
 * @Author yanzt
 * @Date 2019-04-23 16:05:32
 */
@Data
public class RedEnvelopeDetail extends GenericModel<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  字段名称：红包id
     * 
     * 数据库字段信息：red_envelope_id BIGINT(19)
     */
    private Long redEnvelopeId;

    /**
     *  字段名称：领取用户id
     * 
     * 数据库字段信息：receive_id BIGINT(19)
     */
    private Long receiveId;

    /**
     *  字段名称：领取时间
     * 
     * 数据库字段信息：receive_date TIMESTAMP(19)
     */
    private Date receiveDate;

    /**
     *  字段名称：领取金额
     * 
     * 数据库字段信息：receive_money DECIMAL(17)
     */
    private BigDecimal receiveMoney;

}