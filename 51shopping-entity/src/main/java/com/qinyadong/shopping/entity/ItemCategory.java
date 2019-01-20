package com.qinyadong.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qinyadong.shopping.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/15
 */
@Getter
@Setter
@TableName("tb_item_category")
public class ItemCategory extends BaseEntity {

    @TableField("parent_id")
    private Long parentId;
    private String name;
    @TableField("type_id")
    private Long typeId;
}
