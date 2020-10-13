package com.kokoyuo.text.jc.demo.jdk8;

/**
 * @author lixuanwen
 * @date 2019-12-16 15:25
 */
public class Test {

    public static String tt(StringFunction<String> a, StringFunction<String> b,String str){
        return a.reduce(b).apply(str);
    }

    public static void main(String[] args) {
        int i = 10;
    }
}
