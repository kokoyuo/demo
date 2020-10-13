package com.kokoyuo.text.jc.demo.lambda.function;

import com.kokoyuo.text.jc.demo.lambda.result.ResultCode;
import com.kokoyuo.text.jc.demo.lambda.result.ResultUtil;

/**
 * @author lixuanwen
 * @date 2020-01-06 15:10
 */
@FunctionalInterface
public interface FacadeFunction<T> {

    Object result(T t);

    default FacadeFunction er(FacadeFunction s){
        return o -> {
            try {
                return s.result(o);
            } catch (Exception e){
                System.out.println("exception");
                return ResultUtil.createFailedResult(ResultCode.PARAMS_IS_NULL);
            }
        };
    }
}
