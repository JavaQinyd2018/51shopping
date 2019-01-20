package com.qinyadong.shopping.integration.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.ContentRpcService;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.BaseIntegration;
import com.qinyadong.shopping.integration.ContentIntegration;
import com.qinyadong.shopping.vo.ContentVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
@Service
public class ContentIntegrationImpl extends BaseIntegration implements ContentIntegration {

    @Reference
    private ContentRpcService contentRpcService;

    @Override
    public Result<List<ContentVo>> getCategoryId(Long categoryId) {
        return processResponse(contentRpcService.getByCategoryId(buildRequest(categoryId)));
    }
}
