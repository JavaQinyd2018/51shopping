package com.qinyadong.shopping.integration.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.SellerRpcService;
import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.common.RpcRequest;
import com.qinyadong.shopping.integration.BaseIntegration;
import com.qinyadong.shopping.integration.SellerIntegration;
import com.qinyadong.shopping.ro.AddSellerRo;
import com.qinyadong.shopping.ro.SearchSellerRo;
import com.qinyadong.shopping.ro.UpdatePasswordRo;
import com.qinyadong.shopping.ro.UpdateSellerRo;
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
    public Result<Void> addSeller(AddSellerRo addSellerRo) {
        RpcRequest<AddSellerRo> rpcRequest = this.buildRequest(addSellerRo);
        return processResponse(sellerRpcService.addSeller(rpcRequest));
    }

    @Override
    public Result<SellerVo> getBySellerId(String sellerId) {
        RpcRequest<String> rpcRequest = buildRequest(sellerId);
        return processResponse(sellerRpcService.getBySellerId(rpcRequest));
    }

    @Override
    public Result<Void> updateSeller(UpdateSellerRo sellerVo) {
        return processResponse(sellerRpcService.updateSeller(buildRequest(sellerVo)));
    }

    @Override
    public Result<String> getPasswordBySellerId(String sellerId) {
        return processResponse(sellerRpcService.getPasswordBySellerId(buildRequest(sellerId)));
    }

    @Override
    public Result<Void> updatePassword(UpdatePasswordRo updatePasswordRo) {
        return processResponse(sellerRpcService.updatePassword(buildRequest(updatePasswordRo)));
    }


}
