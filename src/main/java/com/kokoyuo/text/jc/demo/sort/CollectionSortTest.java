package com.kokoyuo.text.jc.demo.sort;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lixuanwen
 * @date 2020-10-13 13:41
 */
public class CollectionSortTest {

    public static void main(String[] args) {
        List<Long> prices = new ArrayList<>();
        prices.add(1L);
        prices.add(5L);
        prices.add(3L);
        prices.sort(Comparator.comparing(o->-o));
        System.out.println(JSONObject.toJSONString(prices));

        for (int i = 0; i < 0; i++) {
            System.out.println("------");
        }
    }
}
