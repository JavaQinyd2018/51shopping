package com.qinyadong.shopping.api;

import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.ro.AddContentRo;
import com.qinyadong.shopping.ro.SearchContentRo;
import com.qinyadong.shopping.ro.UpdateContentRo;
import com.qinyadong.shopping.vo.ContentVo;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
public interface ContentRpcService {

    /**
     * 分页查询
     * @param rpcRequest
     * @return
     */
    RpcResponse<PageResult<ContentVo>> searchByPage(@NotNull(message = "广告查询对象不为空") RpcRequest<PageRo<SearchContentRo>> rpcRequest);

    /**
     * 添加
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> addContent(@NotNull(message = "添加广告对象不为空") RpcRequest<AddContentRo> rpcRequest);

    /**
     * 更新内容
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> updateContent(@NotNull(message = "更新广告对象不为空") RpcRequest<UpdateContentRo> rpcRequest);

    /**
     * 根据ID查询
     * @param request
     * @return
     */
    RpcResponse<ContentVo> getById(@NotNull(message = "Id请求对象不能为空") RpcRequest<Long> request);

    /**
     * 批量删除
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> batchDelete(@NotNull(message = "删除请求对象不能为空") RpcRequest<List<Long>> rpcRequest);

    /**
     * 根据类目ID查询
     * @param rpcRequest
     * @return
     */
    RpcResponse<List<ContentVo>> getByCategoryId(@NotNull(message = "查询请求对象不能为空") RpcRequest<Long> rpcRequest);
}
