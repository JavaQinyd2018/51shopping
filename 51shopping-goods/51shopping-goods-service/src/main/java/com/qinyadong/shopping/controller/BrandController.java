package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.service.BrandService;
import com.qinyadong.shopping.vo.BrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/28
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/listByName")
    public List<BrandVo> listByName(@RequestParam String name) {
        return brandService.listByName(name);
    }
}
