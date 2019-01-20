package com.qinyadong.shopping.vo;

import com.qinyadong.shopping.base.BaseVo;
import lombok.*;

import java.util.Date;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/4
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TypeTemplateVo extends BaseVo {

    private Long id;
    private String name;
    private String specIds;
    private String brandIds;
    private String customAttributeItems;
    private Date createDate;
}
