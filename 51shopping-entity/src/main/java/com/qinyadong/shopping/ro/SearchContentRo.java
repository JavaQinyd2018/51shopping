package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchContentRo extends BaseRo {

    private Long categoryId;
    private String title;
}
