package com.qinyadong.shopping.api;

import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.ro.AddTypeTemplateRo;
import com.qinyadong.shopping.ro.SearchTypeTemplateRo;
import com.qinyadong.shopping.ro.UpdateTypeTemplateRo;
import com.qinyadong.shopping.vo.TypeTemplateVo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/4
 */
public interface TypeTemplateRpcService {

    /**
     * 分页查询Rpc接口
     * @param rpcRequest
     * @return
     */
    RpcResponse<PageResult<TypeTemplateVo>> searchByPage(@NotNull(message = "分页请求对象不能为空") RpcRequest<PageRo<SearchTypeTemplateRo>> rpcRequest);

    /**
     * 添加
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> addTypeTemplate(@NotNull(message = "添加模板远程请求不能为空") RpcRequest<AddTypeTemplateRo> rpcRequest);

    /**
     * ID获取
     * @param id
     * @return
     */
    RpcResponse<TypeTemplateVo> getById(@NotNull(message = "请求的模板ID不能为空") Long id);

    /**
     * 修改远程请求
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> updateTypeTemplate(@NotNull(message = "添修改模板远程请求不能为空") RpcRequest<UpdateTypeTemplateRo> rpcRequest);

    /**
     * 删除
     * @param rpcRequest
     * @return
     */
    RpcResponse<Void> deleteById(@NotNull(message = "删除模板请求不能为空") RpcRequest<List<Long>> rpcRequest);
}
