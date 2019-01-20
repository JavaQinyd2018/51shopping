package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/19
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateContentCategory extends BaseRo {

    @NotNull(message = "内容目录ID不能为空")
    private Long id;
    @NotBlank(message = "类目名称不能为空")
    private String name;
}
