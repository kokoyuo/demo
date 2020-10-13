package com.kokoyuo.text.jc.demo.thread;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @author lixuanwen
 * @date 2020-08-05 14:24
 */
public class InsertSort {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        int size = 10;
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println(JSONObject.toJSONString(list));

        list.add(0, "1111");
        list.parallelStream().collect(Collectors.toList());

        System.out.println(JSONObject.toJSONString(list));
    }
}
