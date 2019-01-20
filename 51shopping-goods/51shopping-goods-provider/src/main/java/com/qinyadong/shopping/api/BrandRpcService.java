package com.qinyadong.shopping.api;

import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.ro.BrandRo;
import com.qinyadong.shopping.ro.SearchBrandRo;
import com.qinyadong.shopping.ro.UpdateBrandRo;
import com.qinyadong.shopping.vo.BrandOptionVo;
import com.qinyadong.shopping.vo.BrandVo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/28
 */
public interface BrandRpcService {

    /**
     * 通过http请求实现--测试
     * @param name
     * @return
     */
    List<BrandVo> listByName(@NotBlank(message = "名称不能为空") String name);

    /**
     * 分页
     * @param roRpcRequest
     * @return
     */
    RpcResponse<PageResult<BrandVo>> searchByPage(RpcRequest<PageRo<SearchBrandRo>> roRpcRequest);

    /**
     * 添加品牌信息
     * @param request 品牌请求信息
     * @return 结果信息
     */
    RpcResponse<Void> add(@NotNull(message = "品牌不能为空") RpcRequest<BrandRo> request);

    /**
     * 品牌是否已经存在
     * @param brandName
     * @return
     */
    RpcResponse<Boolean> hasExisted(@NotBlank(message = "查询的品牌名称不能为空") String brandName);

    /**
     * 通过Id查询
     * @param id
     * @return
     */
    RpcResponse<BrandVo> getById(@NotNull(message = "品牌Id不能为空") Long id);

    /**
     *修改
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> update(@NotNull(message = "品牌对象不能为空") RpcRequest<UpdateBrandRo> rpcRequest);

    /**
     * 通过ID删除
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> batchDeleteById(@NotNull(message = "品牌ID不能为空") RpcRequest<List<Long>> rpcRequest);

    /**
     * 获取品牌列表
     * @return
     */
    RpcResponse<List<BrandOptionVo>> getBrandList();
}
