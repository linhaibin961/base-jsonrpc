package com.lhb.common.utils;

/**
 * Created by huangdongbin on 2016/3/16.
 */
public class Holder<T> {
    private T value;

    public Holder(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
