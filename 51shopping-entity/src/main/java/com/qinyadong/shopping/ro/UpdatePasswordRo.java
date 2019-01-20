package com.qinyadong.shopping.ro;

import com.qinyadong.shopping.base.BaseRo;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdatePasswordRo extends BaseRo {

    @NotBlank(message = "商家ID不能为空")
    private String sellerId;
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
    @NotBlank(message = "再次确认密码不能为空")
    private String newPasswordConfirm;
}
