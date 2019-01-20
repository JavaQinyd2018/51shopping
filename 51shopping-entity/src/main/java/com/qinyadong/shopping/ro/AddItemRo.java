package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddItemRo extends BaseRo {

    @NotBlank(message = "标题不能为空")
    private String title;
    private String sellPoint;
    @NotNull(message = "商品价格不能为空")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal price;
    private Integer stockCount;
    private Integer num;
    private String barcode;
    private String image;
    private Long categoryId;
    private String status;
    private String itemSn;
    @NotNull(message = "商品价格不能为空")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal costPirce;
    @NotNull(message = "商品价格不能为空")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal marketPrice;
    private String isDefault;
    @NotNull(message = "商品ID不能为空")
    private Long goodsId;
    private String sellerId;
    private String cartThumbnail;
    private String category;
    private String brand;
    private String spec;
    private String seller;
}
