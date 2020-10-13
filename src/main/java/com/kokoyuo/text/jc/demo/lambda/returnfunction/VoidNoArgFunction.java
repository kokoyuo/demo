package com.kokoyuo.text.jc.demo.lambda.returnfunction;

/**
 * @author lixuanwen
 * @date 2020-01-07 15:58
 */
@FunctionalInterface
public interface VoidNoArgFunction {
    void apply();

    default VoidArgsFunction getArgsApply(VoidNoArgFunction function){
        return objs -> function.apply();
    }

    default VoidArgsFunction getArgsApply(){
        return objs -> this.apply();
    }
}
