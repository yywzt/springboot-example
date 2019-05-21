package com.example.yyw.enums;

/**
 * 红包类型枚举类
 *
 * @author ywyw2424@foxmail.com
 * @date 2019/4/23 16:37
 */
public enum EnvelopeEnum {

    /**
     * 私发
     */
    SINGLE(1, "私发"),
    /**
     * 群发
     */
    QUN(2, "群发");

    private Integer code;
    private String value;

    public static EnvelopeEnum getEnvelopeByCode(Integer code) {
        for (EnvelopeEnum envelopeEnum : EnvelopeEnum.values()) {
            if (envelopeEnum.getCode().equals(code)) {
                return envelopeEnum;
            }
        }
        return null;
    }

    EnvelopeEnum(Integer code, String value) {
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
