package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchSellerRo extends BaseRo {
    private String sellerId;
    private String name;
    private String nickName;
    private String linkmanName;
}
