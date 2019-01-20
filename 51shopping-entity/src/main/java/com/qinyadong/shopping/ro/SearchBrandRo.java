package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/29
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SearchBrandRo extends BaseRo {

    private String name;
    private String firstChar;
}
