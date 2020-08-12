package com.example.demo.exception;

/**
 * 自定义异常
 */
public class DemoException extends RuntimeException {
    private static final long serialVersionUID = 3461465005694735857L;
    private int errcode = -1;
    private String message;

    public DemoException(int errcode, String message) {
        super(message);
        this.message = message;
        this.errcode = errcode;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
