package com.kokoyuo.text.jc.demo.lambda.returnfunction;

import com.kokoyuo.text.jc.demo.lambda.voidfunction.ArgsFunction;

/**
 * @author lixuanwen
 * @date 2020-01-07 15:58
 */
@FunctionalInterface
public interface NoArgFunction<R> {

    R apply();

    default ArgsFunction<R> getArgsApply(){
        return objs -> this.apply();
    }
}
