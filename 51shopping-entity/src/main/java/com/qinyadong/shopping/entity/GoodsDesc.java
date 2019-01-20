package com.qinyadong.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qinyadong.shopping.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@Getter
@Setter
@TableName("tb_goods_desc")
public class GoodsDesc extends BaseEntity{
    @TableField("goods_id")
    private Long goodsId;
    private String introduction;
    @TableField("specification_items")
    private String specificationItems;
    @TableField("custom_attribute_items")
    private String customAttributeItems;
    @TableField("item_images")
    private String itemImages;
    @TableField("package_list")
    private String packageList;
    @TableField("sale_service")
    private String saleService;
}
