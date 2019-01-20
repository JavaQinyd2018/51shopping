package com.qinyadong.shopping.integration.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.SellerRpcService;
import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.integration.BaseIntegration;
import com.qinyadong.shopping.integration.SellerIntegration;
import com.qinyadong.shopping.ro.AuditSellerRo;
import com.qinyadong.shopping.ro.SearchAllSellerRo;
import com.qinyadong.shopping.ro.SearchSellerRo;
import com.qinyadong.shopping.vo.SellerVo;
import org.springframework.stereotype.Service;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
@Service
public class SellerIntegrationImpl extends BaseIntegration implements SellerIntegration {

    @Reference
    private SellerRpcService sellerRpcService;

    @Override
    public PageResult<SellerVo> searchByPage(PageRo<SearchSellerRo> searchSellerRoPageRo) {
        RpcResponse<PageResult<SellerVo>> rpcResponse = sellerRpcService.searchByPage(buildRequest(searchSellerRoPageRo));
        if (RpcCode.RESPONSE_SUCCESS.equals(rpcResponse.getCode())) {
            return rpcResponse.getData();
        }
        return null;
    }

    @Override
    public Result<SellerVo> getBySellerId(String sellerId) {
        RpcRequest<String> rpcRequest = buildRequest(sellerId);
        return processResponse(sellerRpcService.getBySellerId(rpcRequest));
    }

    @Override
    public Result<Void> auditSellerStatus(AuditSellerRo auditSellerRo) {
        return processResponse(sellerRpcService.updateSellerStatus(buildRequest(auditSellerRo)));
    }

    @Override
    public PageResult<SellerVo> searchAllByPage(PageRo<SearchAllSellerRo> sellerRoPageRo) {
        RpcResponse<PageResult<SellerVo>> rpcResponse = sellerRpcService.searchAllByPage(buildRequest(sellerRoPageRo));
        if (RpcCode.RESPONSE_SUCCESS.equals(rpcResponse.getCode())) {
            return rpcResponse.getData();
        }
        return null;
    }
}
