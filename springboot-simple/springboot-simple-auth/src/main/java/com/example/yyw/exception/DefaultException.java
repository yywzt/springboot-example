package com.example.yyw.exception;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/5 15:09
 * @describe
 */
public class DefaultException extends RuntimeException {

    public DefaultException() {
        super();
    }

    public DefaultException(String message) {
        super(message);
    }
}
