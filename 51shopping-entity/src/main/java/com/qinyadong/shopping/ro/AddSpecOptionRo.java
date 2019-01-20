package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class AddSpecOptionRo extends BaseRo {

    @NotBlank(message = "规格项名称不能为空")
    private String optionName;
    @NotNull(message = "顺序不能为空")
    private Integer orders;
}
