package com.kokoyuo.text.jc.demo.lambda.query;

import lombok.Data;

/**
 * @author lixuanwen
 * @date 2019-09-25 11:38
 */
@Data
public class ItemComboSearchOption extends BaseQueryOption{

    /**
     * 营业点id
     */
    private Long saleSiteId;

    /**
     *套餐商品名
     */
    private String itemName;

    /**
     * 套餐商品编码
     */
    private String itemCode;

    /**
     * 套餐子商品的名称
     */
    private String childItemName;

    /**
     * 套餐子商品的sku编码
     */
    private String childSkuCode;

    /**
     * 商品状态
     * (1:待审核,2:审核不通过,3:销售中,4:下架,5:禁售)
     */
    private Integer status;

    /**
     *投放时间起
     */
    private String scheduleStartTime;

    /**
     *投放时间止
     */
    private String scheduleEndTime;

    /**
     * 店铺id
     */
    private Long shopId;

}
