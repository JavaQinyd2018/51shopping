package com.qinyadong.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qinyadong.shopping.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/3
 */
@Getter
@Setter
@TableName("tb_type_template")
public class TypeTemplate extends BaseEntity {

    private String name;
    @TableField("spec_ids")
    private String specIds;
    @TableField("brand_ids")
    private String brandIds;
    @TableField("custom_attribute_items")
    private String customAttributeItems;
}
