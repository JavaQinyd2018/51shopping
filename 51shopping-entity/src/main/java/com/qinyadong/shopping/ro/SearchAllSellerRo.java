package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import com.qinyadong.shopping.emnus.AuditStatus;
import lombok.*;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SearchAllSellerRo extends BaseRo {

    private String sellerId;
    private String name;
    private String nickName;
    private AuditStatus status;
}
