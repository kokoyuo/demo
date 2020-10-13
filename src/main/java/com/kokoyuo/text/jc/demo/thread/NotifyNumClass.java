package com.kokoyuo.text.jc.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lixuanwen
 * @date 2020-09-29 17:25
 */
public class NotifyNumClass {

    private int i = 0;
    private AtomicInteger n;

    public synchronized void increment(){
        if (i > 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        i++;
        this.notify();
    }

    public synchronized void decrement(){
        if (i == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        i--;
        this.notify();
    }


}
