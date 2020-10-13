package com.kokoyuo.text.jc.demo.jdk8;

import netscape.javascript.JSObject;

/**
 * @author lixuanwen
 * @date 2019-12-16 17:12
 */
@FunctionalInterface
public interface ProcessFunction<T, R> {

    R apply(T t);

    default <V> ProcessFunction<T, V> andThan(ProcessFunction<R, V> after){
        return t -> after.apply(apply(t));
    }

    default String toString(ProcessFunction<T, R> t){

        //
        return "";
    }
}
