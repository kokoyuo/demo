package com.kokoyuo.text.jc.demo.other;

import com.kokoyuo.text.jc.demo.lambda.exception.MallItemException;
import com.kokoyuo.text.jc.demo.lambda.query.ItemComboSearchOption;
import com.kokoyuo.text.jc.demo.lambda.result.MallItemResult;
import com.kokoyuo.text.jc.demo.lambda.result.ResultCode;
import com.kokoyuo.text.jc.demo.lambda.result.ResultUtil;
import com.kokoyuo.text.jc.demo.lambda.version2.ResultHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author lixuanwen
 * @date 2020-01-07 22:25
 */
@Slf4j
public class Test {

    public static void main(String[] args) {

//        Long start = System.currentTimeMillis();
//        //for (int i = 0; i < 10000; i++) {
//            facadeItemCode(2L, 2);
//        //}
//        Long end = System.currentTimeMillis();
//        System.out.println(end - start);
//
//        System.out.println("------------------------------");
//        Long start1 = System.currentTimeMillis();
//         //for (int i = 0; i < 10000; i++) {
//            facadeItemCodeLambda(2L, 20);
//        //}
//        Long end1 = System.currentTimeMillis();
//        System.out.println(end - start);
//        System.out.println(end1 - start1);

        /*Long shopId = 200000L;
        facadeItemCodeLambda(shopId, 20);
        System.out.println(shopId);

        ItemComboSearchOption itemComboSearchOption = new ItemComboSearchOption();
        itemComboSearchOption.setItemCode("111");
        testString(itemComboSearchOption);
        System.out.println(itemComboSearchOption.getItemCode());*/

        Integer a = 1;
        if (a != null){

        }

        if (false && bb()){
            System.out.println("aaaaaa");
        }
    }

    public static MallItemResult facadeItemCodeLambda(Long shopId, Integer itemId) {
        return ResultHandler.process(shopId, itemId, (aLong, integer) -> {
            deleteItem(aLong, integer);
        });
    }

    public static MallItemResult facadeItemCodeLambda2(Long shopId, Integer itemId) {
        return ResultHandler.process(() -> deleteItem(shopId, itemId));
    }

    public static MallItemResult facadeItemCode(Long shopId, Integer itemId) {
        try {
            deleteItem(shopId, itemId);
            return ResultUtil.createSuccessResult();
        } catch (MallItemException e) {
            log.warn("facadeItemCode fail, shopId:{}, itemId:{}", shopId, itemId, e);
            return ResultUtil.createFailedResult(ResultCode.getByCode(e.getErrorCode()));
        } catch (Exception e) {
            log.error("facadeItemCode fail, shopId:{}, itemId:{}", shopId, itemId, e);
            return ResultUtil.createFailedResult(ResultCode.FAILURE);
        }
    }


    public static void deleteItem(Long shopId, Integer itemId) {
        System.out.println("删除成功" + shopId.toString() + "-" + itemId);
        throw new MallItemException(ResultCode.DISTRIBUTOR_APPLY_PHONE_CODE_ERROR);
    }

    public static List<String> getItemCodes(Long shopId, Integer itemId) {
        return Arrays.asList("1", "2");
    }


    public void testFinal() {
        final int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);
        // num = 5;
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }

    /*Supplier<Integer> incrementer(Integer start) {
        return new Supplier<Integer>() {
            @Override
            public Integer get() {
                return start++;
            }
        };
    }*/

    public static void testString(ItemComboSearchOption l) {
        l.setItemCode(l.getItemCode()+22222222);
    }

    public static boolean bb(){
        System.out.println("bbbbbbbbbb");
        return true;
    }
}