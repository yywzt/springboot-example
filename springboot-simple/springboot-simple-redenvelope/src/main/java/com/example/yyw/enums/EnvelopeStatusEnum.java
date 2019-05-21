package com.example.yyw.enums;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/4/23 19:52
 * @describe
 */
public enum EnvelopeStatusEnum {

    /**
     * 可领取
     */
    AVAILABLE(1, "可领取"),
    /**
     * 领完了
     */
    FINISHED(2, "领完了"),
    /**
     * 已过期
     */
    EXPIRED(3, "已过期");

    private Integer code;
    private String value;

    public static EnvelopeStatusEnum getEnvelopeStatusByCode(Integer code) {
        for (EnvelopeStatusEnum envelopeStatusEnum : EnvelopeStatusEnum.values()) {
            if (envelopeStatusEnum.getCode().equals(code)) {
                return envelopeStatusEnum;
            }
        }
        return null;
    }

    EnvelopeStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
