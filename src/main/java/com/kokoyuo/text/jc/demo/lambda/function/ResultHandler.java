package com.kokoyuo.text.jc.demo.lambda.function;

import com.alibaba.fastjson.JSONObject;
import com.kokoyuo.text.jc.demo.lambda.exception.MallItemException;
import com.kokoyuo.text.jc.demo.lambda.result.MallItemResult;
import com.kokoyuo.text.jc.demo.lambda.result.ResultCode;
import com.kokoyuo.text.jc.demo.lambda.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * @author lixuanwen
 * @date 2020-01-06 15:01
 */
@Slf4j
public class ResultHandler {

    public static <T> MallItemResult process(FacadeFunction<T> facadeFunction, T t){
        Object result = facadeFunction.er(facadeFunction).result(t);
        if (result instanceof MallItemResult){
            return (MallItemResult) result;
        } else {
            return ResultUtil.createSuccessResult(result);
        }
    }

    public static <T, R> MallItemResult  process( T t, Function<T, R> function){
        System.out.println(JSONObject.toJSONString(Thread.currentThread().getStackTrace()));
        Object r = catchAll(function).apply(t);
        if (r instanceof MallItemResult){
            return (MallItemResult) r;
        } else {
            return ResultUtil.createSuccessResult(r);
        }
    }

    public static Function<? super Object, ? super Object> catchAll(Function t){
        return catchException(catchItemException(t));
    }

    public static Function<? super Object, ? super Object> catchException(Function<? super Object, ? super Object> t){
        return o -> {
            try {
                return t.apply(o);
            } catch (Exception e){
                log.error(e.getMessage(), e);
                return ResultUtil.createFailedResult(ResultCode.SERVER_ERROR);
            }
        };
    }

    public static Function<? super Object, ? super Object> catchItemException(Function<? super Object, ? super Object> t){
        return o -> {
            try {
                return t.apply(o);
            } catch (MallItemException e){
                return ResultUtil.createFailedResult(ResultCode.getByCode(e.getErrorCode()));
            }
        };
    }
}
