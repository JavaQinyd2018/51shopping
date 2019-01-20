package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.ContentIntegration;
import com.qinyadong.shopping.ro.AddContentRo;
import com.qinyadong.shopping.ro.SearchContentRo;
import com.qinyadong.shopping.ro.UpdateContentRo;
import com.qinyadong.shopping.vo.ContentVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
@Slf4j
@RestController
@RequestMapping("/content")
public class ContentController extends BaseController {

    @Autowired
    private ContentIntegration contentIntegration;

    @PostMapping("/searchByPage")
    public PageResult<ContentVo> searchByPage(@RequestBody SearchContentRo searchContentRo,
                                              @RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "10") Integer rows) {
        return contentIntegration.searchByPage(new PageRo<>(searchContentRo, page, rows));
    }

    @PostMapping("/add")
    public Result<Void> addContent(@RequestBody @Validated AddContentRo addContentRo) {
        return contentIntegration.addContent(addContentRo);
    }

    @PutMapping("/update")
    public Result<Void> updateContent(@RequestBody @Validated UpdateContentRo updateContentRo) {
        return contentIntegration.updateContent(updateContentRo);
    }

    @GetMapping("/getById")
    public Result<ContentVo> getById(@RequestParam Long id) {
        return contentIntegration.getById(id);
    }

    @DeleteMapping("/delete")
    public Result<Void> batchDelete(@RequestParam String ids) {
        String[] idStrArray = StringUtils.split(ids, ",");
        List<Long> idLongList = Arrays.stream(idStrArray).map(Long::valueOf).collect(Collectors.toList());
        return contentIntegration.batchDelete(idLongList);
    }
}
