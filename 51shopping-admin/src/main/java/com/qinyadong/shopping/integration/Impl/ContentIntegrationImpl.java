package com.qinyadong.shopping.integration.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.ContentRpcService;
import com.qinyadong.shopping.check.ResponseCheckUtils;
import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.integration.BaseIntegration;
import com.qinyadong.shopping.integration.ContentIntegration;
import com.qinyadong.shopping.ro.AddContentRo;
import com.qinyadong.shopping.ro.SearchContentRo;
import com.qinyadong.shopping.ro.UpdateContentRo;
import com.qinyadong.shopping.vo.ContentVo;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PageResult<ContentVo> searchByPage(PageRo<SearchContentRo> pageRo) {
        RpcResponse<PageResult<ContentVo>> rpcResponse = contentRpcService.searchByPage(buildRequest(pageRo));
        if (ResponseCheckUtils.isSuccess(rpcResponse.getCode())) {
            return rpcResponse.getData();
        }
        return null;
    }

    @Override
    public Result<Void> addContent(AddContentRo addContentRo) {
        return processResponse(contentRpcService.addContent(buildRequest(addContentRo)));
    }

    @Override
    public Result<Void> updateContent(UpdateContentRo updateContentRo) {
        return processResponse(contentRpcService.updateContent(buildRequest(updateContentRo)));
    }

    @Override
    public Result<ContentVo> getById(Long id) {
        return processResponse(contentRpcService.getById(buildRequest(id)));
    }

    @Override
    public Result<Void> batchDelete(List<Long> idList) {
        return processResponse(contentRpcService.batchDelete(buildRequest(idList)));
    }
}
