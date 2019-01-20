package com.qinyadong.shopping.vo;

import com.qinyadong.shopping.base.BaseVo;
import lombok.*;

import java.util.Date;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/19
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemCategoryVo extends BaseVo {

    private Long id;
    private Long parentId;
    private String name;
    private Long typeId;
    private Date createDate;
}
