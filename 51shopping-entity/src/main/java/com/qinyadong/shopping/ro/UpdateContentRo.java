package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import com.qinyadong.shopping.emnus.ContentStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateContentRo extends BaseRo {

    @NotNull(message = "内容ID不能为空")
    private Long id;
    @NotNull(message = "类目ID不能为空")
    private Long categoryId;
    @NotBlank(message = "标题不能为空")
    private String title;
    private String url;
    private String pic;
    private ContentStatus status;
    private Integer sortOrder;
}
