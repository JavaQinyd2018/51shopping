package com.qinyadong.shopping.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.qinyadong.shopping.api.SpecRpcService;
import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.manager.SpecManager;
import com.qinyadong.shopping.ro.AddSpecInfoRo;
import com.qinyadong.shopping.ro.SearchSpecRo;
import com.qinyadong.shopping.ro.UpdateSpecInfoRo;
import com.qinyadong.shopping.service.SpecService;
import com.qinyadong.shopping.vo.SpecInfoVo;
import com.qinyadong.shopping.vo.SpecVo;
import com.qinyadong.shopping.vo.SpecificationOptionVo;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
@Service
@Slf4j
public class SpecRpcServiceImpl implements SpecRpcService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private SpecService specService;

    @Autowired
    private SpecManager specManager;

    @SuppressWarnings("all")
    public PageResult<SpecVo> searchByPage(SearchSpecRo searchSpecRo,
                                           @NotNull(message = "页码不能为空") Integer pageNum,
                                           @NotNull(message = "每页大小不能为空") Integer pageSize) {
        PageResult<SpecVo> pageResult = new PageResult<>();
        Map<String, Object> map = specService.searchByPage(searchSpecRo, pageNum, pageSize);
        pageResult.setTotal((Long) map.get("total"));
        pageResult.setList((List<SpecVo>)map.get("specVoList"));
        return pageResult;
    }

    @Override
    public RpcResponse<PageResult<SpecVo>> searchByPage(@NotNull(message = "分页信息请求不能为空") RpcRequest<PageRo<SearchSpecRo>> rpcRequest) {
        RpcResponse<PageResult<SpecVo>> rpcResponse = new RpcResponse<>();
        PageRo<SearchSpecRo> pageRo = rpcRequest.getRo();
        Map<String, Object> map = specService.searchByPage(pageRo.getSearchRo(), pageRo.getPageNum(), pageRo.getPageSize());
        PageResult<SpecVo> pageResult = new PageResult<>((Long) map.get("total"), (List<SpecVo>)map.get("specVoList"));
        rpcResponse.setData(pageResult);
        return rpcResponse;
    }

    @Override
    public RpcResponse<Void> addSpecInfo(@NotNull(message = "规格信息添加请求不能为空") RpcRequest<AddSpecInfoRo> rpcRequest) {
        try {
             specManager.addSpecInfo(rpcRequest.getRo());
             return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "规格信息添加成功",null);
        } catch (Exception e) {
            log.error("规格信息->【{}】添加失败:{}",rpcRequest.getRo().getSpecification().getSpecName(),e);
            return new RpcResponse<>(RpcCode.SYSTEM_RESPONSE_EXCEPTION, "规格信息添加失败，系统错误",null);
        }
    }

    @Override
    public RpcResponse<Boolean> hasExisted(@NotBlank(message = "规格名称不能为空") String specName) {
        RpcResponse<Boolean> response = new RpcResponse<>();
        Boolean flag = specService.selectByName(specName) != null ? Boolean.TRUE : Boolean.FALSE;
        response.setData(flag);
        return response;
    }

    @Override
    public RpcResponse<SpecInfoVo> getById(@NotNull(message = "规格ID不能为空") Long id) {
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "成功",specManager.getById(id));
    }

    @Override
    public RpcResponse<Void> updateSpecInfo(@NotNull(message = "更新的规格信息不能为空") RpcRequest<UpdateSpecInfoRo> rpcRequest) {
        try {
            specManager.updateSpecInfo(rpcRequest.getRo());
            return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "规格信息更新成功", null);
        } catch (Exception e) {
            log.error("更新规格信息失败,规格ID：{}，错误信息",rpcRequest.getRo().getSpecification().getId(),e);
            return new RpcResponse<>(RpcCode.SYSTEM_RESPONSE_EXCEPTION, "更新规格信息失败，系统错误", null);
        }
    }

    @Override
    public RpcResponse<Void> deleteByIdList(@NotEmpty(message = "删除的ID信息不能为空") RpcRequest<List<Long>> rpcRequest) {
        try {
            specManager.deleteSpecInfo(rpcRequest.getRo());
            return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "规格信息删除成功", null);
        } catch (Exception e) {
            log.error("规格信息删除失败,Id信息为：{}",rpcRequest.getRo());
            return new RpcResponse<>(RpcCode.SYSTEM_RESPONSE_EXCEPTION, "规格信息删除失败,系统错误", null);
        }
    }

    @Override
    public RpcResponse<List<SpecificationOptionVo>> getSpecList() {
        List<SpecificationOptionVo> specVoList = specService.list().stream()
                .map(specification -> new SpecificationOptionVo(specification.getId(), specification.getSpecName()))
                .collect(Collectors.toList());
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "查询成功", specVoList);
    }
}
