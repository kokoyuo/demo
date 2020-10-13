package com.kokoyuo.text.jc.demo.thread;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lixuanwen
 * @date 2020-07-31 20:04
 */
public class ExecutorTest {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(20),new CustomizableThreadFactory("subscribe-notify-thread-"),
            (runnable, executor1) -> {
                try {
                    System.out.println("threadPool is full, activeCount:{} taskCount:{} largestPoolSize:{} completed tasks:{}"+ executor1.getActiveCount()+"-"+ executor1.getTaskCount()+"-"+executor1.getLargestPoolSize()+"-"+executor1.getCompletedTaskCount());
                    executor1.getQueue().put(runnable);
                } catch (Exception e) {
                    System.out.println("threadPool is full, reput fail");
                }
            });

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("---------------------------");
            submit(i);
        }
        // executor.allowCoreThreadTimeOut();
    }

    public static void submit(Integer i){
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println(i+"执行");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
