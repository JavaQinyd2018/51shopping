package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.ContentCategoryIntegration;
import com.qinyadong.shopping.ro.AddContentCategoryRo;
import com.qinyadong.shopping.ro.ContentCategoryVo;
import com.qinyadong.shopping.ro.SearchContentCategoryRo;
import com.qinyadong.shopping.ro.UpdateContentCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/19
 */
@RestController
@RequestMapping("/contentCategory")
public class ContentCategoryController extends BaseController {

    @Autowired
    private ContentCategoryIntegration contentCategoryIntegration;

    @PostMapping("/searchByPage")
    public PageResult<ContentCategoryVo> searchByPage(@RequestBody SearchContentCategoryRo searchContentCategoryRo,
                                                      @RequestParam(defaultValue = "1") Integer page,
                                                      @RequestParam(defaultValue = "10") Integer rows) {
        return contentCategoryIntegration.selectByPage(new PageRo<>(searchContentCategoryRo, page, rows));
    }

    @PostMapping("/add")
    public Result<Void> addContentCategory(@RequestBody AddContentCategoryRo addContentCategoryRo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.processValidation(bindingResult);
        }
        return contentCategoryIntegration.addContentCategory(addContentCategoryRo);
    }

    @PutMapping("/update")
    public Result<Void> updateContentCategory(@RequestBody UpdateContentCategory updateContentCategory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.processValidation(bindingResult);
        }
        return contentCategoryIntegration.updateContentCategory(updateContentCategory);
    }

    @DeleteMapping("/deleteByIds")
    public Result<Void> batchDelete(@RequestParam String ids) {
        String[] idStrArray = StringUtils.split(ids, ",");
        List<Long> idLongList = Arrays.stream(idStrArray).map(Long::valueOf).collect(Collectors.toList());
        return contentCategoryIntegration.batchDelete(idLongList);
    }

    @GetMapping("getById")
    public Result<ContentCategoryVo> getById(@RequestParam Long id) {
        return contentCategoryIntegration.getById(id);
    }

    @GetMapping("/list")
    public Result<List<ContentCategoryVo>> list() {
        return contentCategoryIntegration.list();
    }
}
