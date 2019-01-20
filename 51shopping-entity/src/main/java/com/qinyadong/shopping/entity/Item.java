package com.qinyadong.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qinyadong.shopping.base.BaseEntity;
import com.qinyadong.shopping.base.BaseRo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Yadong Qin
 */
@Getter
@Setter
@TableName("tb_item")
public class Item extends BaseEntity {

    private String title;
    @TableField("sell_point")
    private String sellPoint;

    private BigDecimal price;
    @TableField("stock_count")
    private Integer stockCount;

    private Integer num;

    private String barcode;

    private String image;

    private Long categoryId;

    private String status;
    @TableField("item_sn")
    private String itemSn;
    @TableField("cost_pirce")
    private BigDecimal costPirce;
    @TableField("market_price")
    private BigDecimal marketPrice;
    @TableField("is_default")
    private String isDefault;
    @TableField("goods_id")
    private Long goodsId;
    @TableField("seller_id")
    private String sellerId;
    @TableField("cart_thumbnail")
    private String cartThumbnail;

    private String category;

    private String brand;

    private String spec;

    private String seller;
}