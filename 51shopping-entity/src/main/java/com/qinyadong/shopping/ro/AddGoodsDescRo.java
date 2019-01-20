package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AddGoodsDescRo extends BaseRo {
    private Long goodsId;
    private String introduction;
    private String specificationItems;
    private String customAttributeItems;
    private String itemImages;
    private String packageList;
    private String saleService;
}
