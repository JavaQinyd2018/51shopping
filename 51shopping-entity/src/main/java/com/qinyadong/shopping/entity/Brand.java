package com.qinyadong.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qinyadong.shopping.base.BaseEntity;
import lombok.Data;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/28
 */
@Data
@TableName(value = "tb_brand")
public class Brand extends BaseEntity {

    private String name;
    @TableField(value = "first_char")
    private String firstChar;
}
