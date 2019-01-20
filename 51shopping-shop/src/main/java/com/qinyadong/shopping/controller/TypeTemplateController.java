package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.TypeTemplateIntegration;
import com.qinyadong.shopping.vo.TypeTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController extends BaseController{

    @Autowired
    private TypeTemplateIntegration typeTemplateIntegration;

    @GetMapping("/getById")
    public Result<TypeTemplateVo> getById(@RequestParam Long id) {
        return typeTemplateIntegration.getById(id);
    }

}
