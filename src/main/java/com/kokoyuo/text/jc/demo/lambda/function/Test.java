package com.kokoyuo.text.jc.demo.lambda.function;

import com.alibaba.fastjson.JSONObject;
import com.kokoyuo.text.jc.demo.lambda.exception.MallItemException;
import com.kokoyuo.text.jc.demo.lambda.result.ResultCode;

/**
 * @author lixuanwen
 * @date 2020-01-06 16:07
 */
public class Test {

    public static void main(String[] args) {
        Integer s = 1231;
        System.out.println(JSONObject.toJSONString(ResultHandler.process(s, Test::nullPointE)));
    }

    public static String nullPointE(Integer s){
        throw new MallItemException(ResultCode.DISTRIBUTOR_APPLY_PHONE_CODE_ERROR);
        // System.out.println(s);
       // return s.toString();
    }
}
