package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchSpecRo extends BaseRo {

    private String specName;
}
