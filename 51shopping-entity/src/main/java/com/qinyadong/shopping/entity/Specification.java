package com.qinyadong.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qinyadong.shopping.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
@Getter
@Setter
@TableName("tb_specification")
public class Specification extends BaseEntity {

    @TableField("spec_name")
    private String specName;
}
