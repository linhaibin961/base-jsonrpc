package com.lhb.common.exception;

/**
 * 手机号码已经注册异常
 *
 * @author $id$
 */
public class MobileAlreadyRegisteredException extends RuntimeException {

    public MobileAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
