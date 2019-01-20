package com.qinyadong.shopping.integration.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.BrandRpcService;
import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.integration.BaseIntegration;
import com.qinyadong.shopping.integration.BrandIntegration;
import com.qinyadong.shopping.ro.BrandRo;
import com.qinyadong.shopping.ro.SearchBrandRo;
import com.qinyadong.shopping.ro.UpdateBrandRo;
import com.qinyadong.shopping.vo.BrandOptionVo;
import com.qinyadong.shopping.vo.BrandVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/6
 */
@Service
@Slf4j
public class BrandIntegrationImpl extends BaseIntegration implements BrandIntegration {

    @Reference
    private BrandRpcService brandRpcService;

    @Override
    public Result<List<BrandOptionVo>> getBrandList() {
        RpcResponse<List<BrandOptionVo>> rpcResponse = brandRpcService.getBrandList();
        return this.processResponse(rpcResponse);
    }

    @Override
    public PageResult<BrandVo> searchByPage(PageRo<SearchBrandRo> pageRo) {
        RpcRequest<PageRo<SearchBrandRo>> rpcRequest = this.buildRequest(pageRo);
        RpcResponse<PageResult<BrandVo>> rpcResponse = brandRpcService.searchByPage(rpcRequest);
        return rpcResponse.getData();
    }

    @Override
    public Result<Boolean> hasExisted(String brandName) {
        return this.processResponse(brandRpcService.hasExisted(brandName));
    }

    @Override
    public Result<Void> add(BrandRo brandRo) {
        RpcRequest<BrandRo> rpcRequest = this.buildRequest(brandRo);
        return this.processResponse(brandRpcService.add(rpcRequest));
    }

    @Override
    public Result<BrandVo> getById(Long id) {
        return this.processResponse(brandRpcService.getById(id));
    }

    @Override
    public Result<Void> update(UpdateBrandRo updateBrandRo) {
        RpcRequest<UpdateBrandRo> rpcRequest = this.buildRequest(updateBrandRo);
        return this.processResponse(brandRpcService.update(rpcRequest));
    }

    @Override
    public Result<Void> batchDeleteById(List<Long> idList) {
        RpcRequest<List<Long>> rpcRequest = this.buildRequest(idList);
        return this.processResponse(brandRpcService.batchDeleteById(rpcRequest));
    }
}
