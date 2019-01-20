package com.qinyadong.shopping.vo;

import com.qinyadong.shopping.base.BaseVo;
import com.qinyadong.shopping.emnus.ContentStatus;
import lombok.*;

import java.util.Date;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ContentVo extends BaseVo {

    private Long id;
    private Long categoryId;
    private String title;
    private String url;
    private String pic;
    private ContentStatus status;
    private Integer sortOrder;
    private Date createDate;
}
