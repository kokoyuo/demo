package com.kokoyuo.text.jc.demo.lambda.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.kokoyuo.text.jc.demo.lambda.result.ResultCode.SUCCESS;

/**
 * ConsumerResult.java的实现描述：
 * 系统统一的返回对象。 定义了返回结果（是否成功、返回码、异常信息等）对象类
 *
 * @author liusongbai
 * @date 2017-05-17
 */
@ToString
public class MallItemResult<T> extends BaseResult {

    /**
     * 实际的返回结果
     **/
    @Getter
    @Setter
    private T data;

    public MallItemResult() {
        super(true, SUCCESS.getCode(), SUCCESS.getMsg());
    }

    public MallItemResult(T data, boolean success, int code, String msg) {
        super(success, code, msg);
        this.data = data;
    }

    public static <T> MallItemResult<T> success() {
        return new Builder<T>().success(true).data(null).code(SUCCESS.getCode()).msg(SUCCESS.getMsg()).build();
    }

    public static <T> MallItemResult<T> success(T data) {
        return new Builder<T>().success(true).data(data).code(SUCCESS.getCode()).msg(SUCCESS.getMsg()).build();
    }

    public static <T> MallItemResult<T> fail(int code, String msg) {
        return new Builder<T>().success(false).data(null).code(code).msg(msg).build();
    }

    public static <T> MallItemResult<T> fail(T data, int code, String msg) {
        return new Builder<T>().success(false).data(data).code(code).msg(msg).build();
    }

    public static <T> MallItemResult<T> fail(ResultCode errorCode) {
        return new Builder<T>().success(false).data(null).code(errorCode.getCode()).msg(errorCode.getMsg()).build();
    }

    public static Builder newBuilder() {
        return new Builder<>();
    }

    public static class  Builder<T> extends BaseResult.Builder {

        private T data;

        protected Builder<T> getThis() {
            return this;
        }

        public MallItemResult<T> build() {
            return new MallItemResult<>(data, success, code, msg);
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder<T> success(Boolean success) {
            this.success = success;
            return this;
        }
    }


    @Override
    public String toString() {
        return "MallItemResult{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                '}';
    }
}
