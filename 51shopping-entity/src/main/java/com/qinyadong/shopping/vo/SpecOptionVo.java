package com.qinyadong.shopping.vo;

import com.qinyadong.shopping.base.BaseVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/2
 */
@Getter
@Setter
@ToString
public class SpecOptionVo extends BaseVo {

    private Long id;
    private String optionName;
    private Long specId;
    private Integer orders;
}
