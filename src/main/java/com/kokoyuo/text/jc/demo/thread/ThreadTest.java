package com.kokoyuo.text.jc.demo.thread;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lixuanwen
 * @date 2020-07-20 16:42
 */
public class ThreadTest {

    static ThreadPoolExecutor tp;

    static{
        tp = new ThreadPoolExecutor(3,3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public static void main(String[] args) throws Exception {

        List<String> tasks = new ArrayList<>(200);
        for (int i = 0; i < 200; i++) {
            tasks.add(String.valueOf(i));
        }

        int tnum = tasks.size() / 100;
        CountDownLatch downLatch = new CountDownLatch(tnum);

        Date start = new Date();
        System.out.println("end time:"+ formatDate(start));
        int i = 0;
        while(i*100<tasks.size()){
            List<String> subTask = tasks.subList(i*100, Math.min((i+1)*100, tasks.size()));
            System.out.println("分发任务min:"+subTask.get(0)+",max:"+subTask.get(subTask.size()-1));
            Future<Boolean> submit = tp.submit(generate(subTask, downLatch));
            //tp.invokeAll()
            i++;
        }
        downLatch.await();
        System.out.println("全部执行完毕");
        System.out.println("end time:"+ formatDate(new Date()));
    }

    public static Callable<Boolean> generate(List<String> strings, CountDownLatch downLatch){
        return () -> {
            try {
                if (strings.get(0).length() > 2) {
                    System.out.println("消费数据，预估时间1S" + JSONObject.toJSONString(strings));
                    Thread.sleep(1000);
                    System.out.println("1执行完毕时间"+ formatDate(new Date()));
                    return false;
                } else {
                    System.out.println("消费数据，预估时间10S" + JSONObject.toJSONString(strings));
                    Thread.sleep(10000);
                    System.out.println("2执行完毕时间"+ formatDate(new Date()));
                    return true;
                }
            } catch (Exception e){
                return false;
            } finally {
                downLatch.countDown();
            }
        };
    }

    public static String formatDate(Date t){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(t);
    }
}
