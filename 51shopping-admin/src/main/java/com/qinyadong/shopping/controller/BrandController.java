package com.qinyadong.shopping.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.qinyadong.shopping.api.BrandRpcService;
import com.qinyadong.shopping.check.ResponseCheckUtils;
import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.emnus.ResultStatus;
import com.qinyadong.shopping.integration.BrandIntegration;
import com.qinyadong.shopping.ro.BrandRo;
import com.qinyadong.shopping.ro.SearchBrandRo;
import com.qinyadong.shopping.ro.UpdateBrandRo;
import com.qinyadong.shopping.vo.BrandOptionVo;
import com.qinyadong.shopping.vo.BrandVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/28
 */
@RestController
@RequestMapping("/brand")
@Slf4j
public class BrandController extends BaseController{

    @Reference
    private BrandRpcService brandRpcService;

    @Autowired
    private BrandIntegration brandIntegration;

    @GetMapping("/listByName")
    public List<BrandVo> listByName(@RequestParam String name) {
        log.info("请求参数：【{}】",name);
        return brandRpcService.listByName(name);
    }

    @PostMapping("/searchByPage")
    public PageResult<BrandVo> searchByPage(@RequestBody SearchBrandRo searchBrandRo,
                                            @RequestParam Integer page,
                                            @RequestParam Integer size) {
        log.info("请求参数：{}，当前页：{}，每页大小：{}",searchBrandRo, page, size);
        return brandIntegration.searchByPage(new PageRo<>(searchBrandRo, page, size));
    }

    @PostMapping("/add")
    public Result<Void> add(@RequestBody @Validated BrandRo brandRo, BindingResult bindingResult) {
        log.info("请求参数：{}",brandRo);
        //校验
        if (bindingResult.hasErrors()) {
            return this.processValidation(bindingResult);
        }
        //品牌名称已经存在
        if (brandIntegration.hasExisted(brandRo.getName()).getData()) {
            return new Result<>(false, "品牌已经存在，不允许重复", ResultStatus.REQUEST_ILLEGALITY, null);
        }
        //正常情况
        return brandIntegration.add(brandRo);
    }

    @GetMapping("/getById")
    public Result<BrandVo> getById(@RequestParam Long id) {
        log.info("品牌id：【{}】",id);
        return brandIntegration.getById(id);
    }

    @PostMapping("/update")
    public Result<Void> update(@RequestBody @Validated UpdateBrandRo brandRo, BindingResult bindingResult) {
        log.info("请求参数：{}",brandRo);
        Result<Void> result = new Result<>();
        //校验
        if (bindingResult.hasErrors()) {
            return this.processValidation(bindingResult);
        }
       //品牌已经存在
        if (brandIntegration.hasExisted(brandRo.getName()).getData()) {
            return new Result<>(false, "品牌已经存在，不允许重复", ResultStatus.REQUEST_ILLEGALITY, null);
        }

        return brandIntegration.update(brandRo);
    }

    @DeleteMapping("/deleteByIds")
    public Result<Void> deleteByIds(@RequestParam String ids) {
        log.info("删除品牌请求参数：{}",ids);
        String[] idsArray = ids.split(",");
        List<Long> idList = Lists.newArrayList();
        if (ArrayUtils.isNotEmpty(idsArray)) {
            idList = Arrays.stream(idsArray).map(Long::parseLong).collect(Collectors.toList());
        }
        return brandIntegration.batchDeleteById(idList);
    }

    @GetMapping("/getBrandList")
    public Result<List<BrandOptionVo>> getBrandList() {
        return brandIntegration.getBrandList();
    }
}
