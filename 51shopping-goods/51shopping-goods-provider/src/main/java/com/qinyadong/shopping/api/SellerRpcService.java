package com.qinyadong.shopping.api;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.RpcRequest;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.ro.*;
import com.qinyadong.shopping.vo.SellerVo;

import javax.validation.constraints.NotNull;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
public interface SellerRpcService {

    /**
     * 添加商家
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> addSeller(@NotNull(message = "添加请求的对象不能为空") RpcRequest<AddSellerRo> rpcRequest);

    /**
     * 获取商家
     * @param rpcRequest
     * @return
     */
    RpcResponse<SellerVo> getBySellerId(@NotNull(message = "获取商家信息的对象不能为空") RpcRequest<String> rpcRequest);

    /**
     * 更新
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> updateSeller(@NotNull(message = "修改请求的对象不能为空") RpcRequest<UpdateSellerRo> rpcRequest);

    /**
     * 获取密码
     * @param buildRequest
     * @return
     */
    RpcResponse<String> getPasswordBySellerId(@NotNull(message = "获取请求的对象不能为空") RpcRequest<String> buildRequest);

    /**
     * 更新密码
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> updatePassword(@NotNull(message = "更新密码的对象不能为空") RpcRequest<UpdatePasswordRo> rpcRequest);

    /**
     * 分页查询
     * @param rpcRequest
     * @return
     */
    RpcResponse<PageResult<SellerVo>> searchByPage(@NotNull(message = "分页查询的对象不能为空") RpcRequest<PageRo<SearchSellerRo>> rpcRequest);

    /**
     * 商品审核
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> updateSellerStatus(@NotNull(message = "商家审核请求不能为空") RpcRequest<AuditSellerRo> rpcRequest);

    /**
     * 查询所有
     * @param rpcRequest
     * @return
     */
    RpcResponse<PageResult<SellerVo>> searchAllByPage(@NotNull(message = "分页查询的对象不能为空")RpcRequest<PageRo<SearchAllSellerRo>> rpcRequest);
}
