package com.kokoyuo.text.jc.demo.stream;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lixuanwen
 * @date 2020-06-02 17:52
 */
public class StreamTest2 {

    public static void main(String[] args) {
        /*ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");

        arrayList.stream().filter(String::isEmpty).sorted(String::compareTo).collect(Collectors.toList());

        String ss = "https://mall.fcbox.com/index.html?show=detail&dcode=%s#/index/%s";
        String format = String.format(ss, "", "1001");
        System.out.println(format);
        System.out.println(new Date());*/
        List<User> ls = new ArrayList();
        User u1 = new User();
        u1.uidId = 1L;
        u1.name = null;

        User u2 = new User();
        u2.uidId = 2L;
        u2.name = "11";

        ls.add(u1);
        ls.add(u2);

        Map<Long, String> collect = ls.stream().collect(Collectors.toMap(User::getUidId, User::getName));
        System.out.println(JSONObject.toJSONString(collect));
    }
}
