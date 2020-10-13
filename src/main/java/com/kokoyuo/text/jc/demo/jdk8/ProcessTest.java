package com.kokoyuo.text.jc.demo.jdk8;

import java.util.function.Predicate;

/**
 * @author lixuanwen
 * @date 2019-12-16 17:20
 */
public class ProcessTest {

    public static void main(String[] args) {
        ProcessFunction<Object,Object> pf = o -> {
            if (o instanceof Integer){
                return (Integer) o + 1;
            }else {
                return o;
            }
        };

        ProcessFunction<Object, Object> sf = o -> {
            if (o instanceof Integer){
                return (Integer) o + 100;
            }else {
                return o;
            }
        };
        System.out.println(pf.andThan(sf).andThan(sf).andThan(pf).apply(1));
        System.out.println(pf.andThan(pf).andThan(sf).andThan(pf).apply("1"));

        Object os = new Object();
        Predicate p = Predicate.isEqual(os);
        Predicate.isEqual(new Object());
    }
}
