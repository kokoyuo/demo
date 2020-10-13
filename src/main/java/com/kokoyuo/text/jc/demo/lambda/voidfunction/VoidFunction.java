package com.kokoyuo.text.jc.demo.lambda.voidfunction;

import com.kokoyuo.text.jc.demo.lambda.result.ResultUtil;
import com.kokoyuo.text.jc.demo.lambda.returnfunction.VoidArgsFunction;
import com.kokoyuo.text.jc.demo.lambda.returnfunction.VoidNoArgFunction;
import com.kokoyuo.text.jc.demo.lambda.returnfunction.VoidOneArgFunction;
import com.kokoyuo.text.jc.demo.lambda.returnfunction.VoidTwoArgFunction;

/**
 * @author lixuanwen
 * @date 2020-01-07 16:00
 */
public interface VoidFunction<T,K,V>{

    VoidNoArgFunction apply(VoidNoArgFunction function);

    VoidOneArgFunction<T> apply(VoidOneArgFunction<T> function);

    VoidTwoArgFunction<T,K> apply(VoidTwoArgFunction<T,K> function);

    default VoidArgsFunction getArgsApply(VoidNoArgFunction function){
        return objs -> function.apply();
    }

    default VoidArgsFunction getArgsApply(VoidOneArgFunction<T> function){
        return objs -> function.apply((T) objs[0]);
    }

    default VoidArgsFunction getArgsApply(VoidTwoArgFunction<T,K> function){
        return objs -> function.apply((T) objs[0], (K) objs[1]);
    }

    default ArgsFunction cast(VoidArgsFunction function){
        return objs -> {
            function.apply(objs);
            return ResultUtil.createSuccessResult();
        };
    }
}
