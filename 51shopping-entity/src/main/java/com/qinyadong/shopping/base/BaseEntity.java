package com.qinyadong.shopping.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/27
 */
@Data
public class BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "create_date",fill = FieldFill.INSERT)
    private Date createDate;
    @TableField(value = "update_date",fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
}
