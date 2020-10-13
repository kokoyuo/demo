package com.kokoyuo.text.jc.demo.lambda.version2;

import com.kokoyuo.text.jc.demo.lambda.exception.MallItemException;
import com.kokoyuo.text.jc.demo.lambda.result.MallItemResult;
import com.kokoyuo.text.jc.demo.lambda.result.ResultCode;
import com.kokoyuo.text.jc.demo.lambda.result.ResultUtil;
import com.kokoyuo.text.jc.demo.lambda.returnfunction.*;
import com.kokoyuo.text.jc.demo.lambda.voidfunction.ArgsFunction;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lixuanwen
 * @date 2020-01-07 16:26
 */
@Slf4j
public class ResultHandler {

    // 对外暴露第一层
    public static MallItemResult<Object> process(VoidNoArgFunction function){
        return new ArgsFunction.ResultObjImp<>(function.getArgsApply().cast()).process();
    }

    public static <T> MallItemResult<?> process(T t, VoidOneArgFunction<T> function){
        return new ArgsFunction.ResultObjImp<>(function.getArgsApply().cast(), t).process();
    }

    public static <T, K> MallItemResult<?> process(T t, K k, VoidTwoArgFunction<T,K> function){
        return new ArgsFunction.ResultObjImp<>(function.getArgsApply().cast(), t, k).process();
    }

    public static <R> MallItemResult<R>  process(NoArgFunction<R> function){
        return new ArgsFunction.ResultObjImp<R>(function.getArgsApply()).process();
    }

    public static <R, T> MallItemResult<R> process(T t, OneArgFunction<T,R> function){
        return new ArgsFunction.ResultObjImp<R>(function.getArgsApply(), t).process();
    }

    public static <T, K, R> MallItemResult<R> process(T t, K k, TwoArgFunction<T, K, R> function){
        return new ArgsFunction.ResultObjImp<R>(function.getArgsApply(), t, k).process();
    }

    public static <R> MallItemResult<R> process(ArgsFunction<R> function, Object ... objs){
        return new ArgsFunction.ResultObjImp<R>(function, objs).process();
    }


}
