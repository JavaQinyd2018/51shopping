package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/19
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchItemCategoryRo extends BaseRo {
    private String name;
    @NotNull(message = "父节点ID不能为空")
    private Long parentId;
}
