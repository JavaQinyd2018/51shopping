package com.qinyadong.shopping.integration.Impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.TypeTemplateRpcService;
import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.emnus.ResultStatus;
import com.qinyadong.shopping.integration.BaseIntegration;
import com.qinyadong.shopping.integration.TypeTemplateIntegration;
import com.qinyadong.shopping.ro.AddTypeTemplateRo;
import com.qinyadong.shopping.ro.SearchTypeTemplateRo;
import com.qinyadong.shopping.ro.UpdateTypeTemplateRo;
import com.qinyadong.shopping.vo.TypeTemplateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/6
 */
@Service
@Slf4j
public class TypeTemplateIntegrationImpl extends BaseIntegration implements TypeTemplateIntegration {

    @Reference
    private TypeTemplateRpcService typeTemplateRpcService;

    @Override
    public Result<Void> add(AddTypeTemplateRo addTypeTemplateRo) {
        RpcRequest<AddTypeTemplateRo> rpcRequest = this.buildRequest(addTypeTemplateRo);
            RpcResponse<Void> rpcResponse = typeTemplateRpcService.addTypeTemplate(rpcRequest);
            return this.processResponse(rpcResponse);
    }

    @Override
    public PageResult<TypeTemplateVo> searchByPage(PageRo<SearchTypeTemplateRo> pageRo) {
        RpcRequest<PageRo<SearchTypeTemplateRo>> rpcRequest = this.buildRequest(pageRo);
        RpcResponse<PageResult<TypeTemplateVo>> rpcResponse = typeTemplateRpcService.searchByPage(rpcRequest);
        return rpcResponse.getData();
    }

    @Override
    public Result<TypeTemplateVo> getById(Long id) {
        RpcResponse<TypeTemplateVo> rpcResponse = typeTemplateRpcService.getById(id);
        return this.processResponse(rpcResponse);
    }

    @Override
    public Result<Void> update(UpdateTypeTemplateRo updateTypeTemplateRo) {
        RpcRequest<UpdateTypeTemplateRo> rpcRequest = this.buildRequest(updateTypeTemplateRo);
        RpcResponse<Void> rpcResponse = typeTemplateRpcService.updateTypeTemplate(rpcRequest);
        return this.processResponse(rpcResponse);
    }

    @Override
    public Result<Void> deleteByIds(List<Long> idList) {
        RpcRequest<List<Long>> rpcRequest = this.buildRequest(idList);
        RpcResponse<Void> rpcResponse = typeTemplateRpcService.deleteById(rpcRequest);
        return this.processResponse(rpcResponse);
    }
}
