package com.kokoyuo.text.jc.demo.lambda.returnfunction;

/**
 * @author lixuanwen
 * @date 2020-01-07 16:03
 */
@FunctionalInterface
public interface VoidTwoArgFunction<T,K> {
    void apply(T t, K k);

    default VoidArgsFunction getArgsApply(VoidTwoArgFunction<T,K> function){
        return objs -> function.apply((T) objs[0], (K) objs[1]);
    }

    default VoidArgsFunction getArgsApply(){
        return objs -> this.apply((T) objs[0], (K) objs[1]);
    }
}
