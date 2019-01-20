package com.qinyadong.shopping.api;

import com.qinyadong.shopping.common.RpcRequest;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.ro.AddGoodsInfoRo;

import javax.validation.constraints.NotNull;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
public interface GoodsRpcService {
    /**
     * 添加
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> addGoodsInfo(@NotNull(message = "添加商品请求信息不能为空") RpcRequest<AddGoodsInfoRo> rpcRequest);
}
