package com.kokoyuo.text.jc.demo.logical;

import lombok.Data;

import java.util.List;

/**
 * @author lixuanwen
 * @date 2020-11-06 18:09
 */
@Data
public class SingleRuleLogical {

    private List<Shape> shapes;

    public SingleRuleLogical or(SingleRuleLogical right){
        if (right == null || right.getShapes() == null){
            return this;
        }
        //List<>
        return right;
    }
}
