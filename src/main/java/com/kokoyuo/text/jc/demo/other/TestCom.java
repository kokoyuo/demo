package com.kokoyuo.text.jc.demo.other;

import com.kokoyuo.text.jc.demo.lambda.exception.MallItemException;
import com.kokoyuo.text.jc.demo.lambda.result.ResultCode;

import java.math.BigDecimal;
import java.util.Comparator;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * @author lixuanwen
 * @date 2020-02-07 16:51
 */
public class TestCom {

    public static void main(String[] args) {
        /*judgeGreater(ResultCode.SERVER_ERROR, new BigDecimal(10), new BigDecimal(2), BigDecimal::compareTo);

        judgeGreater(ResultCode.SERVER_ERROR, new BigDecimal(10), new BigDecimal(2), (o1, o2) -> {
            return o1.compareTo(o2);
        });*/

        System.out.println(getSettlementCommissionRatio(100, 1).toString());
    }

    public static <T> void  judgeGreater(ResultCode resultCode, T left, T right, Comparator<? super T> comparator) {
        if (comparator.compare(left, right) > 0) {
            throw new MallItemException(resultCode);
        }
    }

    public Comparable  tt(Comparable c) {
        return o -> c.compareTo(o);
    }

    /**
     * 计算结算主表佣金比例
     * @param settlementAmount
     * @param commissionAmount
     * @return
     */
    private static BigDecimal getSettlementCommissionRatio(int settlementAmount, int commissionAmount){
        return new BigDecimal(commissionAmount).multiply(new BigDecimal(100)).divide(new BigDecimal(commissionAmount), 2, ROUND_HALF_DOWN);
    }
}
