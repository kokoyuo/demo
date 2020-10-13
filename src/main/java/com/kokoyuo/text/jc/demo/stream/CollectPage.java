package com.kokoyuo.text.jc.demo.stream;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lixuanwen
 * @date 2020-08-05 17:37
 */
public class CollectPage {

    public static void main(String[] args) {
        Stream<Integer> limit = Stream.iterate(1, i -> ++i).limit(200000);
        // System.out.println(JSONObject.toJSONString(limit.collect(Collectors.toList())));

        Map<Integer, List<Integer>> collect = limit.parallel().collect(toPageMap(100));
        // Collectors.toList()

        System.out.println(JSONObject.toJSONString(collect));

        BinaryOperator<Map<Integer, List<Object>>> combiner = toPageMap().combiner();

        Map<Integer, List<Object>> ls1 = new HashMap<>();
        List<Object> ll1 = new ArrayList<>();
        ll1.add("1");
        ll1.add("2");
        List<Object> ll2 = new ArrayList<>();
        ll2.add("10");
        ll2.add("20");
        ls1.put(0, ll1);

        Map<Integer, List<Object>> ls2 = new HashMap<>();
        ls2.put(0, ll2);

        Map<Integer, List<Object>> apply = toPageMap().combiner().apply(ls1, ls2);
        System.out.println(JSONObject.toJSONString(apply));

    }

    public static <T> Collector<T, Map<Integer, List<T>>,Map<Integer, List<T>>> toPageMap(int i){
        return toPageMap(i, Function.identity());
    }

    /**
     * 流收集器
     * 将流分页至多个map中
     * 默认按1000 分页
     * 不支持并发流
     * @param <T>
     * @return
     */
    public static <T> Collector<T, Map<Integer, List<T>>,Map<Integer, List<T>>> toPageMap(){
        return toPageMap(0, Function.identity());
    }

    /**
     * 流收集器
     * 将流分页至多个map中
     * 默认按1000 分页
     * 不支持并发流
     * @param i
     * @param finisher
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Collector<T, Map<Integer, List<T>>, R> toPageMap(int i, Function<Map<Integer, List<T>>, R> finisher){

        int initPageSize = i > 0 ? i : 1000;

        return new Collector<T, Map<Integer, List<T>>, R>() {

            private final AtomicInteger seq = new AtomicInteger(0);

            private int pageSize = initPageSize;

            private final Set<Characteristics> characteristics = new HashSet<Characteristics>(Arrays.asList(Characteristics.CONCURRENT));

            @Override
            public Supplier<Map<Integer, List<T>>> supplier() {
                return ConcurrentHashMap::new;
            }

            @Override
            public BiConsumer<Map<Integer, List<T>>, T> accumulator() {
                return (pageMap, t) -> {
                    int tempSeq = seq.getAndIncrement();
                    int currentPage = tempSeq / pageSize;
                    System.out.println(t+"---------->"+tempSeq+"----->"+currentPage);
                    List<T> ls = pageMap.get(currentPage);
                    if (ls == null) {
                        ls = new ArrayList<>();
                        ls.add(t);
                        pageMap.put(currentPage, ls);
                    } else {
                        ls = pageMap.get(currentPage);
                        ls.add(t);
                    }
                };
            }

            @Override
            public BinaryOperator<Map<Integer, List<T>>> combiner() {
                return (lm1, lm2) -> {
                    Optional<Integer> reduce = lm1.values().stream().map(List::size).reduce(Integer::sum);
                    Optional<Integer> reduce2 = lm2.values().stream().map(List::size).reduce(Integer::sum);
                    lm2.forEach((key, ts) -> {
                        List<T> tempList = lm1.getOrDefault(key, new ArrayList<>());
                        tempList.addAll(ts);
                        lm1.put(key, tempList);
                    });
                    Optional<Integer> reduce3 = lm1.values().stream().map(List::size).reduce(Integer::sum);
                    System.out.println("合并前:"+ reduce + reduce2 + "合并后:" + reduce3);
                    return lm1;
                };
            }

            @Override
            public Function<Map<Integer, List<T>>, R> finisher() {
                return finisher;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return characteristics;
            }
        };
    }

    static class CollectorToPage<T> implements Collector<T, Map<Integer, List<T>>, Map<Integer, List<T>>> {

        private final AtomicInteger seq = new AtomicInteger(0);

        private int pageSize = 1000;

        private final Set<Characteristics> characteristics = new HashSet<Characteristics>(Arrays.asList(Characteristics.IDENTITY_FINISH));

        public CollectorToPage(int pageSize) {
            super();
            this.pageSize = pageSize;
        }

        public CollectorToPage() {
            super();
        }

        @Override
        public Supplier<Map<Integer, List<T>>> supplier() {
            return ConcurrentHashMap::new;
        }

        @Override
        public BiConsumer<Map<Integer, List<T>>, T> accumulator() {
            return (pageMap, t) -> {
                synchronized (CollectorToPage.class){


                int tempSeq = seq.getAndIncrement();
                int currentPage = tempSeq / pageSize;
                List<T> ls;
                if (currentPage >= pageMap.size()){
                    ls = Collections.synchronizedList(new ArrayList<>());
                    ls.add(t);
                    pageMap.put(currentPage, ls);
                } else {
                    ls = pageMap.get(currentPage);
                    ls.add(t);
                }
                }
            };
        }

        @Override
        public BinaryOperator<Map<Integer, List<T>>> combiner() {
            return (lm1, lm2) -> {
                lm2.forEach((key, ts) -> {
                    List<T> tempList = lm1.getOrDefault(key, new ArrayList<>());
                    tempList.addAll(ts);
                    lm1.put(key, tempList);
                });
                return lm1;
            };
        }

        @Override
        public Function<Map<Integer, List<T>>, Map<Integer, List<T>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return characteristics;
        }
    }
}
