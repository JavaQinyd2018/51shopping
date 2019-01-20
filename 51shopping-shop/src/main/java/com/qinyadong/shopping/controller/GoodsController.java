package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.GoodsIntegration;
import com.qinyadong.shopping.ro.AddGoodsInfoRo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsIntegration goodsIntegration;

    @PostMapping("/add")
    public Result<Void> addGoodsInfo(@RequestBody AddGoodsInfoRo addGoodsInfoRo,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.processValidation(bindingResult);
        }
        return goodsIntegration.addGoodsInfo(addGoodsInfoRo);
    }
}
