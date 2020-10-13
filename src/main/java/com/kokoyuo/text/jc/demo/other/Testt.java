package com.kokoyuo.text.jc.demo.other;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author lixuanwen
 * @date 2020-03-18 16:46
 */
public class Testt {

    public static void main(String[] args) throws ParseException {
        int a = 1;
        Integer b = null;
        if (!Objects.equals(a,b)){
            System.out.println("11111");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        String dateStr = "2020-4-16 10:14:52";
        String timeStr = "10:14:52";
        Date time = sdf2.parse(timeStr);
        Date date = sdf.parse(dateStr);
        System.out.println(JSONObject.toJSONString(time));
        System.out.println(JSONObject.toJSONString(date));



        String format = "已新增%s张卡券，卡券集ID=%s,关联商品%s-%s";

        System.out.println(format(format, "1" ,"2", "3", null));
    }

    private static String format(String format, Object... args){
        if (args == null){
            return format;
        }
        Object[] objs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            Object temp = args[i];
            if (Objects.isNull(temp)){
                objs[i] = "";
            } else {
                objs[i] = temp;
            }
        }
        return String.format(format, objs);
    }
}
