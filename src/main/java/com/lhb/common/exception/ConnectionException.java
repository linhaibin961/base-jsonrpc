package com.lhb.common.exception;

public class ConnectionException extends BaseException {

    public static final int ERROR_CREATE_CONNECTION = -31996;


    private final int code;

    public ConnectionException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
