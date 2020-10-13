package com.kokoyuo.text.jc.demo.jdk8;

import com.alibaba.fastjson.JSONObject;
import org.quartz.spi.ThreadExecutor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lixuanwen
 * @date 2019-12-18 14:01
 */
public class Main {
    public static void main(String[] args) {
        List<Long> ls = new ArrayList<>();
        ls.add(2L);
        ls.add(8L);
        ls.add(3L);
        ls.add(4L);
        // ls.sort((o1, o2) -> );
        ls.forEach(System.out::println);

        Long[] ls2 = {2L, 8L, 3L, 4L};
        System.out.println(JSONObject.toJSONString(ls));
        System.out.println(JSONObject.toJSONString(ls2));

        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("1", null);
        map.put("2", null);
        System.out.println(JSONObject.toJSONString(map));

        Long a = 7L;
        Integer b = 7;
        System.out.println(a > b);
        System.out.println(a < b);
        // System.out.println(a == b);
        System.out.println(null ==null);

        String refundAmount = "0";
        long realRefundMoney = new BigDecimal(refundAmount).multiply(new BigDecimal(100)).longValue();
    }

    public static Function<String, String> cxf(){
        /*ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute();
        service.submit()*/
        return null;
    }
}
