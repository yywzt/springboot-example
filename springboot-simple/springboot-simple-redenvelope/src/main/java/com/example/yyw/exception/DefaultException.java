package com.example.yyw.exception;

/**
 * @author yanzt
 * @date 2019/4/24 22:54
 * @describe
 */
public class DefaultException extends RuntimeException {

    private String message;

    public DefaultException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
