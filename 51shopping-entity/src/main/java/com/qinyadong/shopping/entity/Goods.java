package com.qinyadong.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qinyadong.shopping.base.BaseEntity;
import com.qinyadong.shopping.emnus.AuditStatus;
import com.qinyadong.shopping.emnus.MarketableType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 *商品表
 */
@Getter
@Setter
@TableName("tb_goods")
public class Goods extends BaseEntity {
    @TableField("seller_id")
    private String sellerId;
    @TableField("goods_name")
    public String goodsName;
    @TableField("default_item_id")
    public Long defaultItemId;
    @TableField("audit_status")
    private AuditStatus auditStatus;
    @TableField("marketable_type")
    private MarketableType marketableType;
    @TableField("brand_id")
    private Long brandId;
    private String caption;
    @TableField("category1_id")
    private Long category1Id;
    @TableField("category2_id")
    private Long category2Id;
    @TableField("category3_id")
    private Long category3Id;
    @TableField("small_pic")
    private String smallPic;
    private BigDecimal price;
    @TableField("type_template_id")
    private Long typeTemplateId;
    @TableField("is_enable_spec")
    private String isEnableSpec;
    @TableField("is_delete")
    private String isDelete;
}
