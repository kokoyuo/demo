package com.kokoyuo.text.jc.demo.lambda.exception;

/**
 * <p>
 * 验证异常
 *
 */
public class ValidationException extends RuntimeException {

    private String errorMsg;

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(Throwable cause, String errorMsg) {
        super(cause);
        this.errorMsg = errorMsg;
    }

    public ValidationException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public final String getErrorMsg() {
        return this.errorMsg;
    }
}
