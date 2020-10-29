package com.kokoyuo.text.jc.demo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lixuanwen
 * @date 2020-10-27 17:03
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch cdl = new CountDownLatch(2);
        cdl.countDown();
        cdl.countDown();
        //cdl.wait();

        Thread tl = new Thread(() -> {
            System.out.println("进入子线程");
            lock.lock();
            System.out.println("子线程获取锁成功");
        });
        tl.setName("子线程");

        /*lock.lock();
        lock.lock();*/

        lock.unlock();
        tl.start();
        //lock.getHoldCount()
        //System.out.println();
        //lock.unlock();


    }
}
