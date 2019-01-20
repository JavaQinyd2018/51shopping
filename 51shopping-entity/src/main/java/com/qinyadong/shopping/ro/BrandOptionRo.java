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
public class BrandOptionRo extends BaseRo {

    @NotNull(message = "品牌ID不能为空")
    private Long id;
    @NotBlank(message = "品牌名称不能为空")
    private String text;
}
