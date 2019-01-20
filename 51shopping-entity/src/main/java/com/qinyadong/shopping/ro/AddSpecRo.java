package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.NotBlank;
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
public class AddSpecRo extends BaseRo {
    @NotBlank(message = "规格名称不能为空")
    private String specName;
}
