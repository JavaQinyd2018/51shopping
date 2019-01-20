package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/19
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddContentCategoryRo extends BaseRo {

    @NotBlank(message = "类目名称不能为空")
    private String name;
}
