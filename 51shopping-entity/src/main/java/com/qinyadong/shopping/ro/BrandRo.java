package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/30
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandRo extends BaseRo {

    @NotBlank(message = "品牌名称不能为空")
    private String name;
    @NotBlank(message = "品牌首字母不能为空")
    @Pattern(regexp="[A-Z]",message="年龄不正确")
    private String firstChar;
}
