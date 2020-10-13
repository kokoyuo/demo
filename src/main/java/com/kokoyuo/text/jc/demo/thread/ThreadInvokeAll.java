package com.kokoyuo.text.jc.demo.thread;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author lixuanwen
 * @date 2020-07-21 10:22
 */
public class ThreadInvokeAll {

    static ThreadPoolExecutor tp;

    static{
        tp = new ThreadPoolExecutor(3,3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public static void main(String[] args) {
        int evenNum = 1000;
        int pageSize = 100;
        List<String> tasks = new ArrayList<>(evenNum);
        for (int i = 0; i < evenNum; i++) {
            tasks.add(String.valueOf(i));
        }

        int tnum = tasks.size() / pageSize;
        Date start = new Date();
        System.out.println("end time:"+ formatDate(start));
        int i = 0;
        List<Callable<Boolean>> callables = new ArrayList<>(tnum);
        while(i*pageSize<tasks.size()){
            List<String> subTask = tasks.subList(i*pageSize, Math.min((i+1)*100, tasks.size()));
            System.out.println("分发任务min:"+subTask.get(0)+",max:"+subTask.get(subTask.size()-1));
            //Future<Boolean> submit = tp.submit(generate(subTask, downLatch));
            //tp.invokeAll()
            callables.add(generate(subTask));
            i++;
        }
        List<Future<Boolean>> futures = null;
        try {
            futures = tp.invokeAll(callables);
        } catch (InterruptedException e) {
            System.out.println("111111111111111");
            e.printStackTrace();
        }
        if (futures != null){
            futures.forEach(booleanFuture -> {
                try {
                    System.out.println(booleanFuture.get());
                } catch (InterruptedException e) {
                    System.out.println("222222222");
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    System.out.println("33333333");
                    e.printStackTrace();
                } catch (Exception e){
                    System.out.println("4444444");
                }
            });
        }
        System.out.println("全部执行完毕");
        System.out.println("end time:"+ formatDate(new Date()));
    }

    public static Callable<Boolean> generate(List<String> strings){
        return () -> {

                if (strings.get(0).length() > 2) {
                    System.out.println("消费数据，预估时间1S" + JSONObject.toJSONString(strings));
                    Thread.sleep(1000);
                    System.out.println(strings.get(0)+"-"+strings.get(strings.size()-1)+"执行1S完毕时间"+ formatDate(new Date()));
                    return false;
                } else {
                    System.out.println("消费数据，预估时间10S" + JSONObject.toJSONString(strings));
                    Thread.sleep(10000);
                    // return true;
                    System.out.println(strings.get(0)+"-"+strings.get(strings.size()-1)+"执行10S完毕时间"+ formatDate(new Date()));
                    throw new NullPointerException();
                }

        };
    }

    public static String formatDate(Date t){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(t);
    }
}
