package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.emnus.ResultStatus;
import com.qinyadong.shopping.integration.SellerIntegration;
import com.qinyadong.shopping.ro.AddSellerRo;
import com.qinyadong.shopping.ro.SearchSellerRo;
import com.qinyadong.shopping.ro.UpdatePasswordRo;
import com.qinyadong.shopping.ro.UpdateSellerRo;
import com.qinyadong.shopping.vo.SellerVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 * 商家
 */
@RestController
@RequestMapping("/seller")
public class SellerController extends BaseController{

    @Autowired
    private SellerIntegration sellerIntegration;

    @PostMapping("/add")
    public Result<Void> addSeller(@RequestBody @Valid AddSellerRo sellerVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.processValidation(bindingResult);
        }
        return sellerIntegration.addSeller(sellerVo);
    }

    @GetMapping("getSellerBySellerId")
    public Result<SellerVo> getBySellerId(@RequestParam String sellerId) {
        return sellerIntegration.getBySellerId(sellerId);
    }

    @PutMapping("/update")
    public Result<Void> updateSeller(@RequestBody @Valid UpdateSellerRo sellerVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.processValidation(bindingResult);
        }
        return sellerIntegration.updateSeller(sellerVo);
    }

    @PostMapping("/updatePassword")
    public Result<Void> updatePassword(@RequestBody @Valid UpdatePasswordRo updatePasswordRo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.processValidation(bindingResult);
        }
        String oldPassword = sellerIntegration.getPasswordBySellerId(updatePasswordRo.getSellerId()).getData();
        if (StringUtils.isBlank(oldPassword)) {
            return new Result<>(false, "原密码不存在", ResultStatus.REQUEST_ILLEGALITY,null);
        }
        if (!StringUtils.equals(updatePasswordRo.getOldPassword(), oldPassword)) {
            return new Result<>(false, "原密码不匹配",ResultStatus.REQUEST_ILLEGALITY, null);
        }
        return sellerIntegration.updatePassword(updatePasswordRo);
    }


}
