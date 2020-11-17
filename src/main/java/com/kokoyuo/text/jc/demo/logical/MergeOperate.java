package com.kokoyuo.text.jc.demo.logical;

/**
 * @author lixuanwen
 * @date 2020-11-09 15:18
 */
public interface MergeOperate<L extends Shape, R extends Shape> {

    default boolean canMerge(L l, R r){
        return false;
    }

    Shape orMerge(L l, R r);

    Shape interMerge(L l, R r);
}
