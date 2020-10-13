package com.kokoyuo.text.jc.demo.lambda.voidfunction;

import com.kokoyuo.text.jc.demo.lambda.exception.MallItemException;
import com.kokoyuo.text.jc.demo.lambda.result.MallItemResult;
import com.kokoyuo.text.jc.demo.lambda.result.ResultCode;
import com.kokoyuo.text.jc.demo.lambda.result.ResultUtil;
import com.kokoyuo.text.jc.demo.lambda.returnfunction.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lixuanwen
 * @date 2020-01-07 16:26
 */
@Slf4j
public class ResultHandler1 {

    // 对外暴露第一层
    public static MallItemResult<?> process(ArgsFunction<MallItemResult<?>> function, Object ... objs){
        return function.apply(objs);
    }

    public static MallItemResult<?> process(VoidNoArgFunction function){
        return catchAndResultCreate(function.getArgsApply().cast()).apply();
    }

    public static <T> MallItemResult<?> process(T t, VoidOneArgFunction<T> function){
        return catchAndResultCreate(function.getArgsApply().cast()).apply(t);
    }

    public static <T, K> MallItemResult<?> process(T t, K k, VoidTwoArgFunction<T,K> function){
        return catchAndResultCreate(function.getArgsApply().cast()).apply(t, k);
    }

    public static MallItemResult<?> process(NoArgFunction function){
        return catchAndResultCreate(function.getArgsApply()).apply();
    }

//    public static <T> MallItemResult<?> process(T t, OneArgFunction<T> function){
//        return catchAndResultCreate(function.getArgsApply()).apply(t);
//    }
//
//    public static <T, K> MallItemResult<?> process(T t, K k, TwoArgFunction<T,K> function){
//        return catchAndResultCreate(function.getArgsApply()).apply(t, k);
//    }

    // 对外暴露第二层

    public static ArgsFunction<MallItemResult> catchAndResultCreate(ArgsFunction function){
        return objs -> {
            try{
                Object result = function.apply(objs);
                if (result instanceof MallItemResult){
                    return (MallItemResult) result;
                } else {
                    return ResultUtil.createSuccessResult(result);
                }
            } catch (MallItemException e){
                // log.warn("error msg:[{}], args:[{}]",e.getErrorMsg() , objs, e);
                StackTraceElement[] stackTraceElements = e.getStackTrace();
                if (stackTraceElements != null && stackTraceElements.length > 0){
                    StackTraceElement innerStackTrace = stackTraceElements[stackTraceElements.length - 1];
                    String className = innerStackTrace.getClassName();
                    String methodName = innerStackTrace.getMethodName();
                    int lineNumber = innerStackTrace.getLineNumber();
                    log.warn("[{}]:[{}] error, lineNumber:[{}], message:[{}], args:[{}]", className, methodName, lineNumber, e.getErrorMsg(), objs);
                }
                return ResultUtil.createFailedResult(ResultCode.getByCode(e.getErrorCode()));
            } catch (Exception e){
                log.error("error msg:[{}], args:[{}]", e.getMessage() , objs, e);
                return ResultUtil.createFailedResult(ResultCode.SERVER_ERROR);
            }
        };
    }

//    public static <R> MallItemResult<R> catchAndResultCreate2(ArgsFunction<R> function, Object ... objs){
//        return new ArgsFunction.ResultObjImp<>(objs, function).process();
//    }

    public static ArgsFunction cast(VoidArgsFunction function){
        return objs -> {
            function.apply(objs);
            return ResultUtil.createSuccessResult();
        };
    }

}
