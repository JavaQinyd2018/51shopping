package com.qinyadong.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qinyadong.shopping.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/7
 */
@Getter
@Setter
@TableName("tb_content_category")
public class ContentCategory extends BaseEntity {

    private String name;
}
