package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.ContentIntegration;
import com.qinyadong.shopping.vo.ContentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
@RestController
@RequestMapping("/content")
public class ContentController extends BaseController {

    @Autowired
    private ContentIntegration contentIntegration;

    @GetMapping("/getByCategoryId")
    public Result<List<ContentVo>> getByCategoryId(@RequestParam Long categoryId) {
        return contentIntegration.getCategoryId(categoryId);
    }
}
