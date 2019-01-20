package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.emnus.AuditStatus;
import com.qinyadong.shopping.integration.SellerIntegration;
import com.qinyadong.shopping.ro.AuditSellerRo;
import com.qinyadong.shopping.ro.SearchAllSellerRo;
import com.qinyadong.shopping.ro.SearchSellerRo;
import com.qinyadong.shopping.vo.SellerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerIntegration sellerIntegration;

    @PostMapping("/searchByPage")
    public PageResult<SellerVo> searchByPage(@RequestBody SearchSellerRo searchSellerRo,
                                             @RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer rows) {
        return sellerIntegration.searchByPage(new PageRo<>(searchSellerRo,page, rows));
    }

    @GetMapping("getSellerBySellerId")
    public Result<SellerVo> getBySellerId(@RequestParam String sellerId) {
        return sellerIntegration.getBySellerId(sellerId);
    }

    @GetMapping("auditSeller")
    public Result<Void> auditSellerStatus(@RequestParam String sellerId,
                                          @RequestParam AuditStatus status) {
        return sellerIntegration.auditSellerStatus(new AuditSellerRo(sellerId, status));
    }

    @PostMapping("/searchAllByPage")
    public PageResult<SellerVo> searchAllByPage(@RequestBody SearchAllSellerRo searchSellerRo,
                                                @RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "10") Integer rows) {
        return sellerIntegration.searchAllByPage(new PageRo<>(searchSellerRo,page, rows));
    }
}
