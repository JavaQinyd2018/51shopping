package com.qinyadong.shopping.vo;

import com.qinyadong.shopping.base.BaseVo;
import lombok.*;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/8
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandOptionVo extends BaseVo {
    private Long id;
    private String text;
}
