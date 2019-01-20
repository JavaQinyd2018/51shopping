package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/6
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddTypeTemplateRo extends BaseRo {

    @NotBlank(message = "类型模板名称不能为空")
    private String name;
    @NotEmpty(message = "规格不能为空")
    private List<SpecificationOptionRo> specIds;
    @NotEmpty(message = "品牌不能为空")
    private List<BrandOptionRo> brandIds;
    private List<CustomAttributeRo> customAttributeItems;
}
