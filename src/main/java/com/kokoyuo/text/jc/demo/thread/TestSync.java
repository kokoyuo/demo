package com.kokoyuo.text.jc.demo.thread;

/**
 * @author lixuanwen
 * @date 2020-10-27 17:34
 */
public class TestSync {

    public static void main(String[] args) {
        Thread tl1 = new Thread(() -> t1());
        Thread tl2 = new Thread(() -> t2());
        tl1.start();
        tl2.start();
    }

    public synchronized static void t1(){
        System.out.println("t1 方法开始执行");
        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1 方法执行完毕");
    }

    public synchronized static void t2(){
        System.out.println("t2 方法执行完毕");
    }
}
