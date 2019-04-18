package com.example.yyw.mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "sfy_pos_order")
public class SfyPosOrder {

    @Id
    private String id;

    @Field("mysqlId")
    private String mysqlId;

    @Field("order_no")
    private String orderNo;

    @Field("sub_order_no")
    private String subOrderNo;

    @Field("pay_order_no")
    private String payOrderNo;

    @Field("pay_channel")
    private String payChannel;

    @Field("pay_money")
    private String payMoney;

    @Field("pay_state")
    private String payState;

    @Field("pay_time")
    private Date payTime;

    @Field("payee")
    private String payee;

    @Field("payer")
    private String payer;

    @Field("payer_phone")
    private String payerPhone;

    @Field("house_id")
    private String houseId;

    @Field("house_no")
    private String houseNo;

    @Field("building_id")
    private String buildingId;

    @Field("building_name")
    private String buildingName;

    @Field("village_id")
    private String villageId;

    @Field("village_name")
    private String villageName;

    @Field("business_type")
    private String businessType;

    @Field("item_id")
    private String itemId;

    @Field("item_name")
    private String itemName;

    @Field("money")
    private String money;

    @Field("late_fee")
    private String lateFee;

    @Field("fee_id")
    private String feeId;

    @Field("cost_peroid")
    private String costPeroid;

    @Field("current_cost_peroid")
    private String currentCostPeroid;

    @Field("remark")
    private String remark;

    @Field("sync_flag")
    private String syncFlag;

    @Field("sync_msg")
    private String syncMsg;

    @Field("sync_url")
    private String syncUrl;

    @Field("sync_fail_time")
    private String syncFailTime;

    @Field("ticket")
    private String ticket;

    @Field("creation_date")
    private Date creationDate;

    @Field("created_by")
    private String createdBy;

    @Field("updation_date")
    private Date updationDate;

    @Field("updated_by")
    private String updatedBy;

    @Field("enabled_flag")
    private String enabledFlag;

    @Field("refund_flag")
    private String refundFlag;

    @Field("refund_sync_flag")
    private String refundSyncFlag;

    @Field("refund_sync_msg")
    private String refundSyncMsg;

    @Field("refund_sync_fail_time")
    private String refundSyncFailTime;

    @Field("refund_time")
    private Date refundTime;

}
