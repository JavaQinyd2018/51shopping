package com.qinyadong.shopping.api;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.RpcRequest;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.entity.ItemCategory;
import com.qinyadong.shopping.ro.SearchItemCategoryRo;
import com.qinyadong.shopping.vo.ItemCategoryVo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/19
 */
public interface ItemCategoryRpcService {

    /**
     * 分页查询
     * @param rpcRequest
     * @return
     */
    RpcResponse<PageResult<ItemCategoryVo>> searchByPage(@NotNull(message = "分页查询请求不能为空")RpcRequest<PageRo<SearchItemCategoryRo>> rpcRequest);

    /**
     * 父ID获取类目分类
     * @param rpcRequest
     * @return
     */
    RpcResponse<List<ItemCategoryVo>> getListByParentId(@NotNull(message = "获取类目的请求不能为空") RpcRequest<Long> rpcRequest);

    /**
     * 根据ID获取
     * @param rpcRequest
     * @return
     */
    RpcResponse<ItemCategoryVo> getById(@NotNull(message = "获取类目的请求不能为空") RpcRequest<Long> rpcRequest);
}
