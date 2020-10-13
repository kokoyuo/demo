package com.kokoyuo.text.jc.demo.thread;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixuanwen
 * @date 2020-07-23 17:10
 */
public class ListDemo {

    public static void main(String[] args) {
        List<String> ss = new ArrayList<>();
        String s = ss.get(16);
        s = "1232131";

        System.out.println(JSONObject.toJSONString(ss));
    }
}
