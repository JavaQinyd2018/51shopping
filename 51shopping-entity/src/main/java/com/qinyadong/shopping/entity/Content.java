package com.qinyadong.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qinyadong.shopping.base.BaseEntity;
import com.qinyadong.shopping.emnus.ContentStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
@Getter
@Setter
@TableName("tb_content")
public class Content extends BaseEntity {

    @TableField("category_id")
    private Long categoryId;
    private String title;
    private String url;
    private String pic;
    private ContentStatus status;
    @TableField("sort_order")
    private Integer sortOrder;
}
