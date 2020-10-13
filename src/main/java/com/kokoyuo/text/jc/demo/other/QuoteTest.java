package com.kokoyuo.text.jc.demo.other;

import com.kokoyuo.text.jc.demo.lambda.query.ItemComboSearchOption;
import com.kokoyuo.text.jc.demo.lambda.result.MallItemResult;
import com.kokoyuo.text.jc.demo.lambda.version2.ResultHandler;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author lixuanwen
 * @date 2020-01-14 17:07
 */
public class QuoteTest {

    public static void main(String[] args) {
        ItemComboSearchOption itemComboSearchOption = new ItemComboSearchOption();
        itemComboSearchOption.setItemCode("111");
        facadeItemCodeLambda(itemComboSearchOption);
        System.out.println(itemComboSearchOption.getItemCode());

        ArrayList<String> ls = new ArrayList<>();
        assert ls == null;
        ls.stream().map(o->o).map(o->o).collect(Collectors.toList());
    }

    public static MallItemResult facadeItemCodeLambda(ItemComboSearchOption itemComboSearchOption){
        itemComboSearchOption = new ItemComboSearchOption();
        return ResultHandler.process(itemComboSearchOption, itemComboSearchOption1 -> {
            // itemComboSearchOption.setItemCode("lambda");
            // itemComboSearchOption.setItemCode("");
            itemComboSearchOption1 = new ItemComboSearchOption();
        });
    }
}
