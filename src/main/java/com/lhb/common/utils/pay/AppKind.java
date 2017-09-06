package com.lhb.common.utils.pay;

/**
 * Created by huangdongbin on 2016/8/3.
 */
public enum AppKind {

    PHONE(0),WECHAT(1),GKD(2);

    private int value;

    AppKind(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
