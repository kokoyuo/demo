package com.kokoyuo.text.jc.demo.jdk8;

import java.util.function.Function;

/**
 * @author lixuanwen
 * @date 2019-12-16 15:00
 */
@FunctionalInterface
public interface StringFunction<T> {

    String apply(T t);

//    default StringFunction<T> andThen(StringFunction<String> after){
//        return (T t) -> after.apply(apply(t));
//    }

    default StringFunction<T> reduce (StringFunction<T> b){
        return t -> b.apply(t) + apply(t);
    }
}