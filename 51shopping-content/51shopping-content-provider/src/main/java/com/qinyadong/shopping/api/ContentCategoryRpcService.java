package com.qinyadong.shopping.api;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.RpcRequest;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.ro.AddContentCategoryRo;
import com.qinyadong.shopping.ro.ContentCategoryVo;
import com.qinyadong.shopping.ro.SearchContentCategoryRo;
import com.qinyadong.shopping.ro.UpdateContentCategory;
import com.qinyadong.shopping.vo.ContentVo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/19
 */
public interface ContentCategoryRpcService {

    /**
     * 分页查询
     * @param request
     * @return
     */
    RpcResponse<PageResult<ContentCategoryVo>> selectByPage(@NotNull(message = "分页请求对象不能为空") RpcRequest<PageRo<SearchContentCategoryRo>> request);

    /**
     * 添加类目
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> addContentCategory(@NotNull(message = "添加请求对象不能为空") RpcRequest<AddContentCategoryRo> rpcRequest);

    /**
     * 修改
     * @param buildRequest
     * @return
     */
    RpcResponse<Void> updateContentCategory(@NotNull(message = "修改请求对象不能为空") RpcRequest<UpdateContentCategory> buildRequest);

    /**
     * 根据ID删除
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> batchDelete(@NotNull(message = "修改请求对象不能为空") RpcRequest<List<Long>> rpcRequest);

    /**
     * ID获取
     * @param rpcRequest
     * @return
     */
    RpcResponse<ContentCategoryVo> getById(@NotNull(message = "根据ID获取请求对象不能为空") RpcRequest<Long> rpcRequest);

    /**
     * 查询所有
     * @return
     */
    RpcResponse<List<ContentCategoryVo>> list();
}
