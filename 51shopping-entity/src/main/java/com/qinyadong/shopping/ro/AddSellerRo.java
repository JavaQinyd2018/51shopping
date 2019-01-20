package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class AddSellerRo extends BaseRo {
    @NotBlank(message = "商家ID不能为空")
    private String sellerId;
    @NotBlank(message = "商家密码不能为空")
    private String password;
    private String nickName;
    @NotBlank(message = "商家名称不能为空")
    private String name;
    @Pattern(regexp = "^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$",message = "手机号码不合法")
    private String mobile;
    private String telephone;
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "邮箱不合法")
    private String email;
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
