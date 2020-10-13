package com.kokoyuo.text.jc.demo.lambda.voidfunction;

import com.kokoyuo.text.jc.demo.lambda.exception.MallItemException;
import com.kokoyuo.text.jc.demo.lambda.result.MallItemResult;
import com.kokoyuo.text.jc.demo.lambda.result.ResultCode;
import com.kokoyuo.text.jc.demo.lambda.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author lixuanwen
 * @date 2020-01-07 20:08
 */
@FunctionalInterface
public interface ArgsFunction<R> {

    R apply(Object... objs);

    default ArgsFunction<R> andThen(Function<R, R> after) {
        Objects.requireNonNull(after);
        return objs -> after.apply(apply(objs));
    }

    abstract class AbstractResultObj<T,R> {
        Object[] data;
        T result;
        ArgsFunction<T> argsFunction;
        Function<R, R> function;

        public AbstractResultObj(Object[] data, ArgsFunction<T> argsFunction, Function<R, R> function) {
            this.data = data;
            this.argsFunction = argsFunction;
            this.function = function;
        }

        public AbstractResultObj(Object[] data, ArgsFunction<T> argsFunction) {
            this.data = data;
            this.argsFunction = argsFunction;
        }

        public R process(){
            if(function != null){
                return finish().andThen(function).apply(data);
            } else {
                return finish().apply(data);
            }
        }

        abstract ArgsFunction<R> finish();

    }

    @Slf4j
    class ResultObjImp <T> extends AbstractResultObj<T, MallItemResult<T>> {

        public ResultObjImp(ArgsFunction<T> argsFunction, Function<MallItemResult<T>, MallItemResult<T>> function,Object... data) {
            super(data, argsFunction, function);
        }

        public ResultObjImp(ArgsFunction<T> argsFunction, Object... data) {
            super(data, argsFunction);
        }

        @Override
        ArgsFunction<MallItemResult<T>> finish() {
            return o -> {
                try{
                    T result = argsFunction.apply(o);
                    if (result instanceof MallItemResult){
                        return (MallItemResult<T>) result;
                    } else {
                        return ResultUtil.createSuccessResult(result);
                    }
                } catch (MallItemException e){
                    StackTraceElement[] stackTraceElements = e.getStackTrace();
                    if (stackTraceElements != null && stackTraceElements.length > 0){
                        StackTraceElement innerStackTrace = stackTraceElements[stackTraceElements.length - 1];
                        String className = innerStackTrace.getClassName();
                        String methodName = innerStackTrace.getMethodName();
                        int lineNumber = innerStackTrace.getLineNumber();
                        log.warn("[{}]:[{}] error, lineNumber:[{}], message:[{}], args:[{}]", className, methodName, lineNumber, e.getErrorMsg(), data);
                    }
                    return ResultUtil.createFailedResult(ResultCode.getByCode(e.getErrorCode()));
                } catch (Exception e){
                    log.error("error msg:[{}], args:[{}]", e.getMessage() , data, e);
                    return ResultUtil.createFailedResult(ResultCode.SERVER_ERROR);
                }
            };
        }
    }
}

