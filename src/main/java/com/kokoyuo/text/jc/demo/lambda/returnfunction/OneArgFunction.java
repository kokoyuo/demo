package com.kokoyuo.text.jc.demo.lambda.returnfunction;


import com.kokoyuo.text.jc.demo.lambda.voidfunction.ArgsFunction;

/**
 * @author lixuanwen
 * @date 2020-01-07 15:57
 */
@FunctionalInterface
public interface OneArgFunction<T, R> {
    R apply(T t);

    default VoidArgsFunction getArgsApply(OneArgFunction<T, R> function){
        return objs -> function.apply((T) objs[0]);
    }

    default ArgsFunction<R> getArgsApply(){
        return objs -> this.apply((T) objs[0]);
    }
}
