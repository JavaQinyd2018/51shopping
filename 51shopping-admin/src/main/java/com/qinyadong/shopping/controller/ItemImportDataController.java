package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.ImportItemDataIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Yadong Qin
 * @Date: 2019/3/2
 */
@RestController
@RequestMapping("/itemImportData")
public class ItemImportDataController extends BaseController {

    @Autowired
    private ImportItemDataIntegration importItemDataIntegration;

    @GetMapping("/importAllItem")
    public Result<Void> importAllItem() {
        return importItemDataIntegration.importData();
    }

}
