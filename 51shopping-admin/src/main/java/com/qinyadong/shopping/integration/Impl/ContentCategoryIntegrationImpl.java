package com.qinyadong.shopping.integration.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.ContentCategoryRpcService;
import com.qinyadong.shopping.check.ResponseCheckUtils;
import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.integration.BaseIntegration;
import com.qinyadong.shopping.integration.ContentCategoryIntegration;
import com.qinyadong.shopping.ro.AddContentCategoryRo;
import com.qinyadong.shopping.ro.ContentCategoryVo;
import com.qinyadong.shopping.ro.SearchContentCategoryRo;
import com.qinyadong.shopping.ro.UpdateContentCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/19
 */
@Service
public class ContentCategoryIntegrationImpl extends BaseIntegration implements ContentCategoryIntegration {

    @Reference
    private ContentCategoryRpcService contentCategoryRpcService;

    @Override
    public PageResult<ContentCategoryVo> selectByPage(PageRo<SearchContentCategoryRo> pageRo) {
        RpcResponse<PageResult<ContentCategoryVo>> resultRpcResponse = contentCategoryRpcService.selectByPage(buildRequest(pageRo));
        if (ResponseCheckUtils.isSuccess(resultRpcResponse.getCode())) {
            return resultRpcResponse.getData();
        }
        return null;
    }

    @Override
    public Result<Void> addContentCategory(AddContentCategoryRo addContentCategoryRo) {
        return processResponse(contentCategoryRpcService.addContentCategory(buildRequest(addContentCategoryRo)));
    }

    @Override
    public Result<Void> updateContentCategory(UpdateContentCategory updateContentCategory) {
        return processResponse(contentCategoryRpcService.updateContentCategory(buildRequest(updateContentCategory)));
    }

    @Override
    public Result<Void> batchDelete(List<Long> idLongList) {
        return processResponse(contentCategoryRpcService.batchDelete(buildRequest(idLongList)));
    }

    @Override
    public Result<ContentCategoryVo> getById(Long id) {
        return processResponse(contentCategoryRpcService.getById(buildRequest(id)));
    }

    @Override
    public Result<List<ContentCategoryVo>> list() {
        return processResponse(contentCategoryRpcService.list());
    }
}
