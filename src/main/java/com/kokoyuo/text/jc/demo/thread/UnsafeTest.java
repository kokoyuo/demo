package com.kokoyuo.text.jc.demo.thread;

import com.alibaba.fastjson.JSONObject;
import sun.misc.Unsafe;
import sun.reflect.misc.ReflectUtil;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author lixuanwen
 * @date 2020-08-14 17:37
 */
public class UnsafeTest {

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        System.out.println(Integer.MIN_VALUE);
        //通过反射获取Unsafe的成员变量theUnsafe
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        //设置为内存可以取
        field.setAccessible(true);
        //获取值
        Unsafe unsafe = (Unsafe)field.get(null);
        printTime();
        Thread clock = new Thread(() -> {
            int i =0;
            long temp = 0;
            for (;;){
                long l = System.currentTimeMillis();
                if (l%1000 == 0 && l != temp){
                    temp = l;
                    System.out.println(++i);
                    if (i == 3){
                        System.out.println("park");
                        unsafe.park(false, 0);
                    }
                }
            }
        });
        clock.start();
        Thread.sleep(5100);
        unsafe.unpark(clock);
        printTime();
    }

    public static void printTime(){
        System.out.println(JSONObject.toJSONString(new Date()));
    }
}
