package com.qinyadong.shopping.common;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/8
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageRo<T> extends BaseRo {

    private T searchRo;
    @NotNull(message = "页数不能为空")
    @Min(value = 1,message = "页数最小不能低于1页")
    private Integer pageNum;
    @NotNull(message = "每页数据大小不嫩为空")
    @Min(value = 1, message = "每页数量不能低于5个")
    @Max(value = 30, message = "每页数量不能高于30个")
    private Integer pageSize;

}
