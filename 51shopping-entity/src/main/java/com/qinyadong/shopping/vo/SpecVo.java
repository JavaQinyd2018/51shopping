package com.qinyadong.shopping.vo;

import com.qinyadong.shopping.base.BaseVo;
import lombok.*;

import java.util.Date;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SpecVo extends BaseVo {

    private Long id;
    private String specName;
    private Date createDate;
}
