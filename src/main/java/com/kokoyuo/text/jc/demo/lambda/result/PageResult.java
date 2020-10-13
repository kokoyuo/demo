package com.kokoyuo.text.jc.demo.lambda.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 类PageResult.java的实现描述：
 * ConsumerResult的子类， 包括页码以及数据总数.
 *
 * @author liusongbai
 * @date 2017-03-14
 */
@ToString
public class PageResult<T> extends MallItemResult {
    /**
     * 页码
     */
    @Getter
    @Setter
    private int pageNum ;

    /**
     * 总记录数
     */
    @Getter
    @Setter
    private int total;

    /**
     * 总页数
     */
    @Setter
    @Getter
    private int pages;

    /**
     * 每页条数
     */
    @Setter
    @Getter
    private int pageSize;

    public PageResult(){
        super(null,true,ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMsg());
    }

    public PageResult(List<T> data,boolean success,int code ,String msg,int pages,int total){
        super(data,success,code,msg);
        this.pages = pages;
        this.total = total;
    }

    @Override
    public List<T> getData() {
        return (List<T>) super.getData();
    }

    public static <T> PageResult success(List<T> list,int pages,int total) {
        return newBuilder().success(true).data(list).code(ResultCode.SUCCESS.getCode()).msg(ResultCode.SUCCESS.getMsg()).pages(pages).total(total).build();
    }

    public static PageResult fail(int code, String msg) {
        return newBuilder().success(false).data(null).code(code).msg(msg).build();
    }

    public static <T> PageResult fail(List<T> list, int code, String msg) {
        return newBuilder().success(false).data(list).code(code).msg(msg).build();
    }

    public static PageResult fail(ResultCode errorCode) {
        return newBuilder().success(false).data(null).code(errorCode.getCode()).msg(errorCode.getMsg()).build();
    }


    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder<T> extends MallItemResult.Builder {

        private int total;

        private int pages;

        private List<T> data;

        @Override
        protected Builder getThis() {
            return this;
        }

        public Builder data(List<T> data) {
            this.data = data;
            return this;
        }

        @Override
        public Builder code(Integer code) {
            this.code = code;
            return this;
        }
        @Override
        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        @Override
        public Builder success(Boolean success) {
            this.success = success;
            return this;
        }

        public Builder pages(int pages){

            this.pages = pages;
            return this;
        }

        public Builder total(int total){
            this.total = total;
            return this;
        }

        @Override
        public PageResult build() {
            return new PageResult(data, success ,code, msg,pages,total);
        }

    }


}
