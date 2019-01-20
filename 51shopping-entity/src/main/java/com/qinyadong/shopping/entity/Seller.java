package com.qinyadong.shopping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qinyadong.shopping.base.BaseEntity;
import com.qinyadong.shopping.emnus.AuditStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
@Getter
@Setter
@TableName("tb_seller")
public class Seller extends BaseEntity {
    @TableField(value = "seller_id")
    private String sellerId;
    private String name;
    @TableField("nick_name")
    private String nickName;
    private String password;
    private String email;
    private String mobile;
    private String telephone;
    private AuditStatus status;
    @TableField("address_detail")
    private String addressDetail;
    @TableField("linkman_name")
    private String linkmanName;
    @TableField("linkman_qq")
    private String linkmanQq;
    @TableField("linkman_mobile")
    private String linkmanMobile;
    @TableField("linkman_email")
    private String linkmanEmail;
    @TableField("license_number")
    private String licenseNumber;
    @TableField("tax_number")
    private String taxNumber;
    @TableField("org_number")
    private String orgNumber;
    private String address;
    @TableField("logo_pic")
    private String logPic;
    private String brief;
    @TableField("legal_person")
    private String legalPerson;
    @TableField("legal_person_card_id")
    private String legalPersonCardId;
    @TableField("bank_user")
    private String bankUser;
    @TableField("bank_name")
    private String bankName;
    @TableField("bank_card")
    private String bankCard;
}
