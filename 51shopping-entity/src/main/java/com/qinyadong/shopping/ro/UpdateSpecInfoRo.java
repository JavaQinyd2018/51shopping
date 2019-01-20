package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import com.qinyadong.shopping.entity.SpecOption;
import com.qinyadong.shopping.entity.Specification;
import lombok.*;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/2
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateSpecInfoRo extends BaseRo {

    private UpdateSpecRo specification;
    private List<UpdateSpecOptionRo> specificationOptionList;

}
