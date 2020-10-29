package com.kokoyuo.text.jc.demo.thread;

/**
 * @author lixuanwen
 * @date 2020-10-28 11:04
 */
public class UnReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        UnReentrantLock lock = new UnReentrantLock();
        Thread tl = new Thread(() -> {
            System.out.println("进入子线程");
            lock.lock();
            System.out.println("子线程获取锁成功");
        });
        tl.setName("子线程");

        lock.lock();

        tl.start();
        System.out.println("主线程沉睡10S");
        Thread.sleep(10000L);
        System.out.println("主线程释放锁");
        lock.unlock();
    }
}
