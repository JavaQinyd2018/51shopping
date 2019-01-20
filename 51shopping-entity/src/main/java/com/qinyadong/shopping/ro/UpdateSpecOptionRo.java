package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/2
 */
@Getter
@Setter
@ToString
public class UpdateSpecOptionRo extends BaseRo {

    @NotNull(message = "规格项ID不能为空")
    private Long id;
    @NotNull(message = "关联的规格ID不能为空")
    private Long specId;
    @NotBlank(message = "规格项名称不能为空")
    private String optionName;
    @NotNull(message = "规格顺序不能为空")
    private Integer orders;
}
