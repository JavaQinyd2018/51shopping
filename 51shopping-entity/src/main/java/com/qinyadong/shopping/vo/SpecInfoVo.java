package com.qinyadong.shopping.vo;

import com.qinyadong.shopping.base.BaseVo;
import com.qinyadong.shopping.entity.SpecOption;
import com.qinyadong.shopping.entity.Specification;
import lombok.*;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/2
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SpecInfoVo extends BaseVo {

    private SpecVo specification;
    private List<SpecOptionVo> specificationOptionList;
}
