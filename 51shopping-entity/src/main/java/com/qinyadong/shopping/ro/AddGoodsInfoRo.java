package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddGoodsInfoRo extends BaseRo {
    @Valid
    private AddGoodsRo goods;
    @Valid
    private AddGoodsDescRo goodsDesc;

    private List<AddItemRo> itemList;
}
