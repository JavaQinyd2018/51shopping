package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/8
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SpecificationOptionRo extends BaseRo {
    @NotNull(message = "规格ID不能为空")
    private Long id;
    @NotBlank(message = "规格名称不能为空")
    private String text;
}
