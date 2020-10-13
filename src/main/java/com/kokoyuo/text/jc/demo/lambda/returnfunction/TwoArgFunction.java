package com.kokoyuo.text.jc.demo.lambda.returnfunction;

import com.kokoyuo.text.jc.demo.lambda.voidfunction.ArgsFunction;

/**
 * @author lixuanwen
 * @date 2020-01-07 16:03
 */
@FunctionalInterface
public interface TwoArgFunction<T, K, R> {
    R apply(T t, K k);

    default ArgsFunction<R> getArgsApply(){
        return objs -> this.apply((T) objs[0], (K) objs[1]);
    }
}
