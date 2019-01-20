package com.qinyadong.shopping.integration.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.ItemCategoryRpcService;
import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.integration.BaseIntegration;
import com.qinyadong.shopping.integration.ItemCategoryIntegration;
import com.qinyadong.shopping.ro.SearchItemCategoryRo;
import com.qinyadong.shopping.vo.ItemCategoryVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/20
 */
@Service
public class ItemCategoryIntegrationImpl extends BaseIntegration implements ItemCategoryIntegration {

    @Reference
    private ItemCategoryRpcService itemCategoryRpcService;

    @Override
    public PageResult<ItemCategoryVo> searchByParentIdForPage(PageRo<SearchItemCategoryRo> pageRo) {
        RpcResponse<PageResult<ItemCategoryVo>> rpcResponse = itemCategoryRpcService.searchByPage(buildRequest(pageRo));
        return rpcResponse.getData();
    }

    @Override
    public Result<List<ItemCategoryVo>> getListByParentId(Long parentId) {
        return processResponse(itemCategoryRpcService.getListByParentId(buildRequest(parentId)));
    }
}
