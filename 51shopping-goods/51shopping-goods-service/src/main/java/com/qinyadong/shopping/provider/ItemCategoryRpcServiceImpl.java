package com.qinyadong.shopping.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.qinyadong.shopping.api.ItemCategoryRpcService;
import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.entity.ItemCategory;
import com.qinyadong.shopping.ro.SearchItemCategoryRo;
import com.qinyadong.shopping.service.ItemCategoryService;
import com.qinyadong.shopping.vo.ItemCategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/19
 */
@Service
@Slf4j
public class ItemCategoryRpcServiceImpl implements ItemCategoryRpcService {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @Autowired
    private Mapper mapper;

    @Override
    public RpcResponse<PageResult<ItemCategoryVo>> searchByPage(@NotNull(message = "分页查询请求不能为空") RpcRequest<PageRo<SearchItemCategoryRo>> rpcRequest) {
        RpcResponse<PageResult<ItemCategoryVo>> rpcResponse = new RpcResponse<>();
        Map<String, Object> map = itemCategoryService.searchByPage(rpcRequest.getRo().getSearchRo(), rpcRequest.getRo().getPageNum(), rpcRequest.getRo().getPageSize());
        PageResult<ItemCategoryVo> pageResult = new PageResult<>((Long) map.get("total"),(List<ItemCategoryVo>)map.get("ItemCategoryVoList"));
        rpcResponse.setMessage("成功");
        rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
        rpcResponse.setData(pageResult);
        return rpcResponse;
    }

    @Override
    public RpcResponse<List<ItemCategoryVo>> getListByParentId(@NotNull(message = "获取类目的请求不能为空") RpcRequest<Long> rpcRequest) {
        List<ItemCategory> itemCategoryList = itemCategoryService.getListByParentId(rpcRequest.getRo());
        List<ItemCategoryVo> itemCategoryVoList = itemCategoryList.stream().map(itemCategory -> mapper.map(itemCategory, ItemCategoryVo.class)).collect(Collectors.toList());
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "查询成功",itemCategoryVoList);
    }

    @Override
    public RpcResponse<ItemCategoryVo> getById(@NotNull(message = "获取类目的请求不能为空") RpcRequest<Long> rpcRequest) {
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "查询成功",mapper.map(itemCategoryService.selectById(rpcRequest.getRo()),ItemCategoryVo.class));
    }
}
