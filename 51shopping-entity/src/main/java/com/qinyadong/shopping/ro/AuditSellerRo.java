package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import com.qinyadong.shopping.emnus.AuditStatus;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AuditSellerRo extends BaseRo {
    @NotNull(message = "商品ID不能为空")
    private String sellerId;
    @NotNull(message = "审核状态不能为空")
    private AuditStatus status;
}
