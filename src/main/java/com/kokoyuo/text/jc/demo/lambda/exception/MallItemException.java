package com.kokoyuo.text.jc.demo.lambda.exception;

import com.kokoyuo.text.jc.demo.lambda.result.ResultCode;

/**
 * 描述：异常定义类
 *
 * @author liusongbai
 * @since 2017-03-14
 */
public class MallItemException extends RuntimeException{

    private static final long serialVersionUID = 1987120495063545921L;

    private int errorCode;
    private String errorMsg;
    private String url;

    public MallItemException(String msg) {
        super(msg);
        errorMsg = msg;
    }

    public MallItemException(Throwable cause) {
        super(cause);
        errorMsg = cause.getMessage();
    }

    public MallItemException(String msg, Throwable cause) {
        super(msg, cause);
        errorMsg = msg;
    }

    public MallItemException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public MallItemException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.errorCode = resultCode.getCode();
        this.errorMsg = resultCode.getMsg();
    }

    public MallItemException(ResultCode resultCode,String url) {
        super(resultCode.getMsg());
        this.errorCode = resultCode.getCode();
        this.errorMsg = resultCode.getMsg();
        this.url=url;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getUrl() {return url;}
    @Override
    public String toString() {
        return "GeneralException [errorCode=" + errorCode + ",errorMsg=" + errorMsg +",url="+url+ "]";
    }
}
