package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/12
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateTypeTemplateRo extends BaseRo {
    @NotNull(message = "模板ID不能为空")
    private Long id;
    @NotBlank(message = "类型模板名称不能为空")
    private String name;
    @NotEmpty(message = "规格不能为空")
    private List<SpecificationOptionRo> specIds;
    @NotEmpty(message = "品牌不能为空")
    private List<BrandOptionRo> brandIds;
    private List<CustomAttributeRo> customAttributeItems;
}
