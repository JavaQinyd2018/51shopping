package com.qinyadong.shopping.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.SpecRpcService;
import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.emnus.ResultStatus;
import com.qinyadong.shopping.integration.SpecIntegration;
import com.qinyadong.shopping.ro.AddSpecInfoRo;
import com.qinyadong.shopping.ro.SearchSpecRo;
import com.qinyadong.shopping.ro.UpdateSpecInfoRo;
import com.qinyadong.shopping.vo.SpecInfoVo;
import com.qinyadong.shopping.vo.SpecVo;
import com.qinyadong.shopping.vo.SpecificationOptionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
@RestController
@RequestMapping("/specification")
public class SpecController extends BaseController{

    @Autowired
    private SpecIntegration specIntegration;

    @PostMapping("/searchByPage")
    public PageResult<SpecVo> searchByPage(@RequestBody SearchSpecRo searchSpecRo,
                                           @RequestParam(value = "page",defaultValue = "1") Integer page,
                                           @RequestParam(value = "size",defaultValue = "10") Integer size) {
        return specIntegration.searchByPage(new PageRo<>(searchSpecRo, page, size));
    }

    @PostMapping("/add")
    public Result<Void> add(@RequestBody @Validated AddSpecInfoRo addSpecInfoRo, BindingResult bindingResult) {

        //校验
        if (bindingResult.hasErrors()) {
            return this.processValidation(bindingResult);
        }
        //规格名称已经存在
        if (specIntegration.hasExisted(addSpecInfoRo.getSpecification().getSpecName())) {
            return new Result<>(false, "该规格名称已存在，规格名称不能重复", ResultStatus.REQUEST_ILLEGALITY, null);
        }

        return specIntegration.addSpecInfo(addSpecInfoRo);
    }

    @GetMapping("/getById")
    public Result<SpecInfoVo> getById(@RequestParam Long id) {
        return specIntegration.getById(id);
    }

    @PutMapping("/update")
    public Result<Void> update(@RequestBody @Validated UpdateSpecInfoRo updateSpecInfoRo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.processValidation(bindingResult);
        }
        //已经存在
        if (specIntegration.hasExisted(updateSpecInfoRo.getSpecification().getSpecName())) {
            return new Result<>(false, "规格名称已经存在，不能重复", ResultStatus.REQUEST_ILLEGALITY, null);
        }

        return specIntegration.updateSpecInfo(updateSpecInfoRo);
    }

    @DeleteMapping("batchDeleteByIds")
    public Result<Void> batchDeleteByIds(@RequestParam String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        List<Long> idList = list.stream().map(Long::parseLong).collect(Collectors.toList());
        return specIntegration.deleteByIdList(idList);
    }

    @GetMapping("getSpecList")
    public Result<List<SpecificationOptionVo>> getSpecList() {
        return specIntegration.getSpecList();
    }
}
