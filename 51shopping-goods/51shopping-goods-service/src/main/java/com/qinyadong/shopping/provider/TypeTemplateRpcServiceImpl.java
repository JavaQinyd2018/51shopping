package com.qinyadong.shopping.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.qinyadong.shopping.api.TypeTemplateRpcService;
import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.entity.TypeTemplate;
import com.qinyadong.shopping.ro.AddTypeTemplateRo;
import com.qinyadong.shopping.ro.SearchTypeTemplateRo;
import com.qinyadong.shopping.ro.UpdateTypeTemplateRo;
import com.qinyadong.shopping.service.TypeTemplateService;
import com.qinyadong.shopping.vo.TypeTemplateVo;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/4
 */
@Service
@Slf4j
public class TypeTemplateRpcServiceImpl implements TypeTemplateRpcService {

    @Autowired
    private TypeTemplateService typeTemplateService;

    @Autowired
    private Mapper mapper;
    @Override
    @SuppressWarnings("all")
    public RpcResponse<PageResult<TypeTemplateVo>> searchByPage(RpcRequest<PageRo<SearchTypeTemplateRo>> rpcRequest) {
        RpcResponse<PageResult<TypeTemplateVo>> rpcResponse = new RpcResponse<>();
        Map<String, Object> searchByPage = typeTemplateService.
                searchByPage(rpcRequest.getRo().getSearchRo(), rpcRequest.getRo().getPageNum(), rpcRequest.getRo().getPageSize());
        PageResult<TypeTemplateVo> pageResult = new PageResult<>((Long) searchByPage.get("total"),(List<TypeTemplateVo>)searchByPage.get("typeTemplateVoList"));
        rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
        rpcResponse.setMessage("查询成功");
        rpcResponse.setData(pageResult);
        return rpcResponse;
    }

    @Override
    public RpcResponse<Void> addTypeTemplate(@NotNull(message = "添加模板远程请求不能为空") RpcRequest<AddTypeTemplateRo> rpcRequest) {
        RpcResponse<Void> rpcResponse = new RpcResponse<>();
        TypeTemplate typeTemplate = new TypeTemplate();
        typeTemplate.setBrandIds(JSONArray.toJSONString(rpcRequest.getRo().getBrandIds()));
        typeTemplate.setName(rpcRequest.getRo().getName());
        typeTemplate.setSpecIds(JSONArray.toJSONString(rpcRequest.getRo().getSpecIds()));
        typeTemplate.setCustomAttributeItems(JSONArray.toJSONString(rpcRequest.getRo().getCustomAttributeItems()));
        try {
            if (typeTemplateService.save(typeTemplate) == 1) {
                rpcResponse.setMessage("模板添加成功");
                rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
            }
        } catch (Exception e) {
           log.error("模板添加失败，系统错误，错误信息：{}",e);
           rpcResponse.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
           rpcResponse.setMessage("模板添加失败，系统错误");
        }
        return rpcResponse;
    }

    @Override
    public RpcResponse<TypeTemplateVo> getById(@NotNull(message = "分页请求对象不能为空") Long id) {
        RpcResponse<TypeTemplateVo> rpcResponse = new RpcResponse<>();
        rpcResponse.setData(mapper.map(typeTemplateService.selectById(id), TypeTemplateVo.class));
        rpcResponse.setMessage("查询成功");
        rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
        return rpcResponse;
    }

    @Override
    public RpcResponse<Void> updateTypeTemplate(@NotNull(message = "添修改模板远程请求不能为空") RpcRequest<UpdateTypeTemplateRo> rpcRequest) {
        RpcResponse<Void> rpcResponse = new RpcResponse<>();
        TypeTemplate typeTemplate = new TypeTemplate();
        typeTemplate.setId(rpcRequest.getRo().getId());
        typeTemplate.setBrandIds(JSONArray.toJSONString(rpcRequest.getRo().getBrandIds()));
        typeTemplate.setName(rpcRequest.getRo().getName());
        typeTemplate.setSpecIds(JSONArray.toJSONString(rpcRequest.getRo().getSpecIds()));
        typeTemplate.setCustomAttributeItems(JSONArray.toJSONString(rpcRequest.getRo().getCustomAttributeItems()));
        try {
            if (typeTemplateService.update(typeTemplate) == 1) {
                rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
                rpcResponse.setMessage("修改模板成功");
            }
        } catch (Exception e) {
            log.error("修改模板失败，系统错误，模板ID为：{}，错误信息为：{}",rpcRequest.getRo().getId(),e);
            rpcResponse.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
            rpcResponse.setMessage("修改模板失败，系统错误");
        }
        return rpcResponse;
    }

    @Override
    public RpcResponse<Void> deleteById(@NotNull(message = "删除模板请求不能为空") RpcRequest<List<Long>> rpcRequest) {
        try {
            typeTemplateService.deleteByIdList(rpcRequest.getRo());
            return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "删除模板成功", null);
        } catch (Exception e) {
            log.error("删除模板失败，系统错误：Id信息：{}，错误信息：{}", rpcRequest.getRo(), e);
            return new RpcResponse<>(RpcCode.SYSTEM_RESPONSE_EXCEPTION,"删除模板失败，系统错误",null);
        }
    }
}
