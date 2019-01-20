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
public class UpdateSpecRo extends BaseRo {

    @NotNull(message = "修改时ID不能为空")
    private Long id;
    @NotBlank(message = "规格名称不能为空")
    private String specName;

}
