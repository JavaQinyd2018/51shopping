package com.qinyadong.shopping.api;

import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.ro.AddSpecInfoRo;
import com.qinyadong.shopping.ro.SearchSpecRo;
import com.qinyadong.shopping.ro.UpdateSpecInfoRo;
import com.qinyadong.shopping.vo.SpecInfoVo;
import com.qinyadong.shopping.vo.SpecVo;
import com.qinyadong.shopping.vo.SpecificationOptionVo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
public interface SpecRpcService {

    /**
     * 分页搜索查询
     * @param rpcRequest
     * @return
     */
    RpcResponse<PageResult<SpecVo>> searchByPage(@NotNull(message = "分页信息请求不能为空") RpcRequest<PageRo<SearchSpecRo>> rpcRequest);

    /**
     * 添加规格信息
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> addSpecInfo(@NotNull(message = "规格信息添加请求不能为空") RpcRequest<AddSpecInfoRo> rpcRequest);

    /**
     * 判断是否已存在
     * @param specName
     * @return
     */
    RpcResponse<Boolean> hasExisted(@NotBlank(message = "规格名称不能为空") String specName);

    /**
     *根据Id查询
     * @param id
     * @return
     */
    RpcResponse<SpecInfoVo> getById(@NotNull(message = "规格ID不能为空") Long id);

    /**
     * 修改
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> updateSpecInfo(@NotNull(message = "更新的规格信息不能为空") RpcRequest<UpdateSpecInfoRo> rpcRequest);

    /**
     * 删除的ID信息不能为空
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> deleteByIdList(@NotEmpty(message = "删除的ID信息不能为空") RpcRequest<List<Long>> rpcRequest);

    /**
     * 获取规格信息
     * @return
     */
    RpcResponse<List<SpecificationOptionVo>> getSpecList();
}
