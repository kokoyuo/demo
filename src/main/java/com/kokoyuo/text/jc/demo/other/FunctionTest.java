package com.kokoyuo.text.jc.demo.other;

import com.alibaba.fastjson.JSONObject;
import com.kokoyuo.text.jc.demo.lambda.exception.MallItemException;
import com.kokoyuo.text.jc.demo.lambda.result.MallItemResult;
import com.kokoyuo.text.jc.demo.lambda.result.ResultCode;
import com.kokoyuo.text.jc.demo.lambda.result.ResultUtil;
import com.kokoyuo.text.jc.demo.lambda.version2.ResultHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author lixuanwen
 * @date 2020-01-08 13:48
 */
@Slf4j
public class FunctionTest {

    public static void main(String[] args) {

        MallItemResult r1 = facadeItemCode(1L,10);

        System.out.println("------------------------------");

        MallItemResult r2 = facadeItemCodeLambda(1L, 30);

        System.out.println(JSONObject.toJSONString(r1));
        System.out.println("------------------------------");
        System.out.println(JSONObject.toJSONString(r2));
    }

    public static List<String> getItemCodes(Long shopId, Integer itemId){
        throw new MallItemException(ResultCode.DISTRIBUTOR_APPLY_PHONE_CODE_ERROR);
        //System.out.println("getItemCodes");
        //return Arrays.asList("1","2", itemId.toString());
    }

    public static MallItemResult<List<String>> facadeItemCodeLambda(Long shopId, Integer itemId){
        return ResultHandler.process(shopId, itemId, (aLong, integer) -> {
            return getItemCodes(aLong, integer);
        });
    }

    public static MallItemResult facadeItemCode(Long shopId, Integer itemId){
        try{
            List<String> string = getItemCodes(shopId, itemId);
            return ResultUtil.createSuccessResult(string);
        } catch (MallItemException e){
            System.out.println("facadeItemCode error :" + e.getErrorMsg());
            log.warn("facadeItemCode fail, shopId:{}, itemId:{}", shopId, itemId, e);
            return ResultUtil.createFailedResult(ResultCode.getByCode(e.getErrorCode()));
        } catch (Exception e){
            log.error("facadeItemCode fail, shopId:{}, itemId:{}", shopId, itemId, e);
            return ResultUtil.createFailedResult(ResultCode.FAILURE);
        }
    }
}
