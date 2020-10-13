package com.kokoyuo.text.jc.demo.lambda.returnfunction;

import com.kokoyuo.text.jc.demo.lambda.result.ResultUtil;
import com.kokoyuo.text.jc.demo.lambda.voidfunction.ArgsFunction;

/**
 * @author lixuanwen
 * @date 2020-01-07 16:10
 */
public interface VoidArgsFunction {

    void apply(Object... objs);

    default ArgsFunction<Object> cast(VoidArgsFunction function){
        return objs -> {
            function.apply(objs);
            return ResultUtil.createSuccessResult();
        };
    }

    default ArgsFunction<Object> cast(){
        return objs -> {
            this.apply(objs);
            return ResultUtil.createSuccessResult();
        };
    }
}
