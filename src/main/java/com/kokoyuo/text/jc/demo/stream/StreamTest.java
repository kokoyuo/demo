package com.kokoyuo.text.jc.demo.stream;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lixuanwen
 * @date 2020-02-01 15:27
 */
public class StreamTest {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");

        //arrayList.retainAll()
        //arrayList.stream().filter(String::isEmpty).sorted().collect(Collectors.toList());

        Stream<String> as = arrayList.stream();
       // as.forEach(System.out::println);
       // as.forEach(System.out::println);

        arrayList.stream().filter(s -> {
            System.out.println("filter1:" + s);
            return true;
        }).map(s -> {
            System.out.println("map:" + s);
            return Integer.parseInt(s);
        }).filter(integer -> {
            System.out.println("filter2:" + integer.toString());
            return integer >= 1;
        }).sorted((o1, o2) -> {
            System.out.println("sort 执行"+o1+"--"+o2);
            return o1.compareTo(o2);
        }).filter(integer -> {
            System.out.println("filter3:" + integer.toString());
            return integer >= 1;
        }).collect(Collectors.toList());
        // arrayList.stream().collect(Collectors.reducing())

        Collectors.toList();

        // arrayList.parallelStream().reduce((s, s2) -> )

        Collector<Long, List<Long>, List<Long>> toList = new Collector<Long, List<Long>, List<Long>>() {
            @Override
            public Supplier<List<Long>> supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<List<Long>, Long> accumulator() {
                return List::add;
            }

            @Override
            public BinaryOperator<List<Long>> combiner() {
                return (longs, longs2) ->  {
                    System.out.println("111111111111111111");
                    longs.addAll(longs2);
                    return longs;
                };
            }

            @Override
            public Function<List<Long>, List<Long>> finisher() {
                return Function.identity();
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
            }
        };
        BiConsumer accumulator = toList.accumulator();
        // accumulator.accept();


        Stream<Long> stringStream = Stream.of(2L, 4L, 6L, 8L,2L, 4L, 6L, 8L,2L, 4L, 6L, 8L);
        List<Long> collect = stringStream.parallel().collect(toList);

        // collect.stream().collect(Collectors.toMap())
        System.out.println(JSONObject.toJSONString(collect));


        Stream<Long> longStream = Stream.of(2L, 4L, 6L, 8L,2L, 4L, 6L, 8L,2L, 4L, 6L, 8L);
        Map<String, String> collect1 = longStream.collect(Collectors.toMap(Object::toString, o -> String.valueOf(o+1), (s, s2) -> s));
        System.out.println(JSONObject.toJSONString(collect1));
    }
}
