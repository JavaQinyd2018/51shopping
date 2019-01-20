package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.ItemCategoryIntegration;
import com.qinyadong.shopping.ro.SearchItemCategoryRo;
import com.qinyadong.shopping.vo.ItemCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/searchByParentIdForPage")
    public PageResult<ItemCategoryVo> searchByParerntIdForPage(@RequestBody SearchItemCategoryRo searchItemCategoryRo,
                                                               @RequestParam Long parentId,
                                                               @RequestParam(defaultValue = "1") Integer page,
                                                               @RequestParam(defaultValue = "10") Integer rows) {
        searchItemCategoryRo.setParentId(parentId);
        return itemCategoryIntegration.searchByParentIdForPage(new PageRo<>(searchItemCategoryRo, page, rows));
    }

    @GetMapping("getListByParentId")
    public Result<List<ItemCategoryVo>> getListByParentId(@RequestParam Long parentId) {
        return itemCategoryIntegration.getListByParentId(parentId);
    }
}
