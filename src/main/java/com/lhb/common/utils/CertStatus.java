package com.lhb.common.utils;

public enum CertStatus {
    /**
     * 审核中
     */
    IN_REVIEW(0),

    /**
     * 已通过
     */
    PASSED(1),

    /**
     * 未通过
     */
    REJECTED(2);

    private byte value;


    CertStatus(int value) {
        this.value = (byte) value;
    }

    /**
     * 通过数据库中的值返回对应的类型.
     *
     * @param b 数据库中的值
     * @return 对应的证书类型
     */
    public static CertStatus valueOf(byte b) {
        switch (b) {
            case 0:
                return IN_REVIEW;
            case 1:
                return PASSED;
            case 2:
                return REJECTED;
            default:
                throw new IllegalArgumentException("不支持的枚举量:" + b);
        }
    }

    /**
     * 数据库内存储的值.
     * 通过这个值和数据库内部数据进行交换
     *
     * @return 数据库保存的值.
     */
    public byte getValue() {
        return value;
    }
}
