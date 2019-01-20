package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddSpecInfoRo extends BaseRo {

    @NotNull(message = "规格不能为空")
    private AddSpecRo specification;
    @NotEmpty(message = "规格项不能为空")
    private List<AddSpecOptionRo> specificationOptionList;
}
