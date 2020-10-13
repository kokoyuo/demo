package com.kokoyuo.text.jc.demo.lock;

import lombok.SneakyThrows;

/**
 * @author lixuanwen
 * @date 2020-07-08 15:13
 */
public class DeadLockClass {

    static final Object lockA = new Object();

    static final Object lockB = new Object();

    public static void main(String[] args) {

        Thread ta = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (lockA){
                    Thread.sleep(10000);
                    System.out.println(Thread.currentThread().getName()+":thread get lockA");
                    synchronized (lockB){
                        System.out.println(Thread.currentThread().getName()+":thread get all lock");
                    }
                }
            }
        });

        Thread tb = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (lockB){
                    Thread.sleep(10000);
                    System.out.println(Thread.currentThread().getName()+":thread get lockB");
                    synchronized (lockA){
                        System.out.println(Thread.currentThread().getName()+":thread get all lock");
                    }
                }
            }
        });
        ta.start();
        tb.start();
    }
}
