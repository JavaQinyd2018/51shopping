package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.ItemCategoryIntegration;
import com.qinyadong.shopping.ro.SearchItemCategoryRo;
import com.qinyadong.shopping.vo.ItemCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/20
 */
@RestController
@RequestMapping("/itemCategory")
public class ItemCategoryController extends BaseController {

    @Autowired
    private ItemCategoryIntegration itemCategoryIntegration;

    @GetMapping("/getListByParentId")
    public Result<List<ItemCategoryVo>> getListByParentId(@RequestParam Long parentId) {
        return itemCategoryIntegration.getListByParentId(parentId);
    }

    @GetMapping("/getById")
    public Result<ItemCategoryVo> getById(@RequestParam Long id) {
        return itemCategoryIntegration.getById(id);
    }
}
