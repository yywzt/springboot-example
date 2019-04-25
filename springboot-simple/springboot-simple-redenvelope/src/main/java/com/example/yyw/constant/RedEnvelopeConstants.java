package com.example.yyw.constant;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/4/25 16:00
 * @describe 红包常量类
 */
public class RedEnvelopeConstants {


    //私发红包数量 1
    public static final Integer ONE_SINGLE_RED_ENVE_LOPE_COUNT = 1;
    //私发红包数量 0
    public static final Integer ZERO_SINGLE_RED_ENVE_LOPE_COUNT = 0;

    /**
     * 发红包
     * */
    public static final String RECEIVE_ID_NOTNULL  = "接收者不能为空";
    public static final String QUN_ID_NOTNULL  = "群id不能为空";
    public static final String RED_ENVELOPE_COUNT_ERROR  = "数量不合法";
    public static final String RED_ENVELOPE_SEND_FAILURE  = "发送失败";


    /**
     * 领取红包
     * */
    public static final String NOT_SENDID_SAME_RECEIVEID = "不能给自己发红包啊";
    //红包领取状态
    public static final String RECEIVED = "你已经领取过此红包了";
    //红包领取状态
    public static final String RECEIVE_FAILURE = "领取失败";
    //红包不存在
    public static final String NOT_RED_ENVELOPE = "红包不存在";

    public static final String RED_ENVELOPE_TYPE_ERROR = "不能识别的红包类型";
    public static final String MONEY_INSUFFICIENT = "金额不足";

}
