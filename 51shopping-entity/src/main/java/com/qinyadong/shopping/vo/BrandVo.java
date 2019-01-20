package com.qinyadong.shopping.vo;

import com.qinyadong.shopping.base.BaseVo;
import lombok.*;

import java.util.Collection;
import java.util.Date;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/28
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandVo extends BaseVo {

    private Long id;
    private String name;
    private String firstChar;
    private Date createDate;
}
