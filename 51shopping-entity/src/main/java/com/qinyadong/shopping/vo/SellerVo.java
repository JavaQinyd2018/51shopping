package com.qinyadong.shopping.vo;

import com.qinyadong.shopping.base.BaseVo;
import com.qinyadong.shopping.emnus.AuditStatus;
import lombok.*;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SellerVo extends BaseVo {
    private Long id;
    private String sellerId;
    private String name;
    private String nickName;
    /**
     * 公司手机
     */
    private String mobile;
    /**
     * 公司电话
     */
    private String telephone;
    private String email;
    private AuditStatus status;
    private String addressDetail;
    private String linkmanName;
    private String linkmanQq;
    private String linkmanMobile;
    private String linkmanEmail;
    private String licenseNumber;
    private String taxNumber;
    private String orgNumber;
    private String legalPerson;
    private String legalPersonCardId;
    private String bankUser;
    private String bankName;
    private String bankCard;

}
