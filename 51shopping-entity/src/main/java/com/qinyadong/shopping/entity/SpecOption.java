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
@TableName("tb_specification_option")
@Setter
@Getter
public class SpecOption extends BaseEntity {

    @TableField("option_name")
    private String optionName;
    @TableField("spec_id")
    private Long specId;
    private Integer orders;
}
