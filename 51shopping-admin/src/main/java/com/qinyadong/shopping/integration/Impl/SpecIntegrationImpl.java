package com.qinyadong.shopping.integration.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.SpecRpcService;
import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.integration.BaseIntegration;
import com.qinyadong.shopping.integration.SpecIntegration;
import com.qinyadong.shopping.ro.AddSpecInfoRo;
import com.qinyadong.shopping.ro.SearchSpecRo;
import com.qinyadong.shopping.ro.UpdateSpecInfoRo;
import com.qinyadong.shopping.vo.SpecInfoVo;
import com.qinyadong.shopping.vo.SpecVo;
import com.qinyadong.shopping.vo.SpecificationOptionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/7
 */
@Slf4j
@Service
public class SpecIntegrationImpl extends BaseIntegration implements SpecIntegration {

    @Reference
    private SpecRpcService specRpcService;

    @Override
    public Result<List<SpecificationOptionVo>> getSpecList() {
        RpcResponse<List<SpecificationOptionVo>> rpcResponse = specRpcService.getSpecList();
        return this.processResponse(rpcResponse);
    }

    @Override
    public PageResult<SpecVo> searchByPage(PageRo<SearchSpecRo> pageRo) {
        RpcRequest<PageRo<SearchSpecRo>> rpcRequest = this.buildRequest(pageRo);
        RpcResponse<PageResult<SpecVo>> rpcResponse = specRpcService.searchByPage(rpcRequest);
        return rpcResponse.getData();
    }

    @Override
    public Boolean hasExisted(String specName) {
        return this.processResponse(specRpcService.hasExisted(specName)).getData();
    }

    @Override
    public Result<Void> addSpecInfo(AddSpecInfoRo addSpecInfoRo) {
        RpcRequest<AddSpecInfoRo> rpcRequest = this.buildRequest(addSpecInfoRo);
        return this.processResponse(specRpcService.addSpecInfo(rpcRequest));
    }

    @Override
    public Result<SpecInfoVo> getById(Long id) {
        return this.processResponse(specRpcService.getById(id));
    }

    @Override
    public Result<Void> updateSpecInfo(UpdateSpecInfoRo updateSpecInfoRo) {
        RpcRequest<UpdateSpecInfoRo> rpcRequest = this.buildRequest(updateSpecInfoRo);
        return this.processResponse(specRpcService.updateSpecInfo(rpcRequest));
    }

    @Override
    public Result<Void> deleteByIdList(List<Long> idList) {
        RpcRequest<List<Long>> rpcRequest = this.buildRequest(idList);
        return this.processResponse(specRpcService.deleteByIdList(rpcRequest));
    }
}
