package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateBrandRo extends BaseRo {
    @NotNull(message = "ID不能为NUll")
    private Long id;
    @NotBlank(message = "品牌名称不能为空")
    private String name;
    @NotBlank(message = "品牌首字母不能为空")
    @Pattern(regexp="[A-Z]",message="首字母格式不正确")
    private String firstChar;
}
