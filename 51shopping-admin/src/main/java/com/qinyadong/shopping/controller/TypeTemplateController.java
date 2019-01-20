package com.qinyadong.shopping.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.TypeTemplateRpcService;
import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.TypeTemplateIntegration;
import com.qinyadong.shopping.ro.AddTypeTemplateRo;
import com.qinyadong.shopping.ro.SearchTypeTemplateRo;
import com.qinyadong.shopping.ro.UpdateTypeTemplateRo;
import com.qinyadong.shopping.vo.TypeTemplateVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/4
 */
@Slf4j
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController extends BaseController {

    @Autowired
    private TypeTemplateIntegration typeTemplateIntegration;


    @PostMapping("/searchByPage")
    public PageResult<TypeTemplateVo> searchByPage(@RequestBody SearchTypeTemplateRo searchTypeTemplateRo,
                                                 @RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size) {
        return typeTemplateIntegration.searchByPage(new PageRo<>(searchTypeTemplateRo, page, size));
    }

    @PostMapping("/add")
    public Result<Void> add(@RequestBody @Valid AddTypeTemplateRo addTypeTemplateRo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.processValidation(bindingResult);
        }
        return typeTemplateIntegration.add(addTypeTemplateRo);
    }

    @GetMapping("/getById")
    public Result<TypeTemplateVo> getById(@RequestParam Long id) {
        return typeTemplateIntegration.getById(id);
    }

    @PutMapping("/update")
    public Result<Void> update(@RequestBody @Valid UpdateTypeTemplateRo updateTypeTemplateRo,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.processValidation(bindingResult);
        }

        return typeTemplateIntegration.update(updateTypeTemplateRo);
    }

    @DeleteMapping("deleteByIds")
    public Result<Void> deleteByIds(@RequestParam String ids) {
        List<String> idStringList = Arrays.asList(StringUtils.split(ids, ","));
        List<Long> idList = idStringList.stream().map(Long::parseLong).collect(Collectors.toList());
        return typeTemplateIntegration.deleteByIds(idList);
    }
}
