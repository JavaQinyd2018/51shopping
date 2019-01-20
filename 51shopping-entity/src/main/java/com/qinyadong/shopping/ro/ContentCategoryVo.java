package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseVo;
import lombok.*;

import java.util.Date;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/19
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContentCategoryVo extends BaseVo {

    private Long id;
    private String name;
    private Date createDate;
}
