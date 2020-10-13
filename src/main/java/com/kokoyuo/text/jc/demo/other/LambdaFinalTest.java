package com.kokoyuo.text.jc.demo.other;

import java.util.function.Supplier;

/**
 * @author lixuanwen
 * @date 2020-05-05 00:23
 */
public class LambdaFinalTest {

    public static String st = "staticString";

    public static void main(String[] args) {
        String version = "1.8";
        Supplier supplier = new Supplier() {
            @Override
            public Object get() {
                return "version";
            }
        };
        version = "1.7";

        doSomething(() -> System.out.println("12345"));
    }

    static void doSomething(SingleFunction sf){
        sf.apply();
    }
}
