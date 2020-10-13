package com.kokoyuo.text.jc.demo.lambda.returnfunction;


/**
 * @author lixuanwen
 * @date 2020-01-07 15:57
 */
@FunctionalInterface
public interface VoidOneArgFunction<T> {
    void apply(T t);

    default VoidArgsFunction getArgsApply(VoidOneArgFunction<T> function){
        return objs -> function.apply((T) objs[0]);
    }

    default VoidArgsFunction getArgsApply(){
        return objs -> this.apply((T) objs[0]);
    }
}
