package com.qinyadong.shopping.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.qinyadong.shopping.api.BrandRpcService;
import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.config.ProjectConfiguration;
import com.qinyadong.shopping.emnus.ResultStatus;
import com.qinyadong.shopping.entity.Brand;
import com.qinyadong.shopping.log.SystemLog;
import com.qinyadong.shopping.ro.BrandRo;
import com.qinyadong.shopping.ro.SearchBrandRo;
import com.qinyadong.shopping.ro.UpdateBrandRo;
import com.qinyadong.shopping.service.BrandService;
import com.qinyadong.shopping.vo.BrandOptionVo;
import com.qinyadong.shopping.vo.BrandVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/28
 */
@Service
@Slf4j
public class BandRpcServiceImpl implements BrandRpcService {

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private ProjectConfiguration configuration;

    @Autowired
    private BrandService brandService;

    @Autowired
    private Mapper mapper;

    @Override
    public List<BrandVo> listByName(String name) {
        log.info("查询皮牌名称：【{}】",name);
        HttpGet get = new HttpGet(String.format("%s%s%s",configuration.getUrl(),"/brand/listByName?name=",name ));
        List<BrandVo> brandVoList = Lists.newArrayList();
        try {
            HttpResponse response = httpClient.execute(get);
            if (response != null) {
                String json = EntityUtils.toString(response.getEntity());
                brandVoList =  JSONArray.parseArray(json, BrandVo.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return brandVoList;
    }

    @SystemLog(value = "品牌分页查询")
    @Override
    @SuppressWarnings("all")
    public RpcResponse<PageResult<BrandVo>> searchByPage(RpcRequest<PageRo<SearchBrandRo>> rpcRequest) {
        RpcResponse<PageResult<BrandVo>> rpcResponse = new RpcResponse<>();
        Map<String, Object> page = brandService.searchByPage(rpcRequest.getRo().getSearchRo(), rpcRequest.getRo().getPageNum(), rpcRequest.getRo().getPageSize());
        PageResult<BrandVo> pageResult = new PageResult<>((Long) page.get("total"),(List<BrandVo>)page.get("brandVoList"));
        rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
        rpcResponse.setMessage("查询成功");
        rpcResponse.setData(pageResult);
        return rpcResponse;
    }

    @SystemLog("品牌添加")
    @Override
    public RpcResponse<Void> add(@NotNull(message = "品牌不能为空") RpcRequest<BrandRo> rpcRequest) {
        RpcResponse<Void> rpcResponse = new RpcResponse<>();
        try {
            if (brandService.save(mapper.map(rpcRequest.getRo(), Brand.class)) == 1) {
                rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
                rpcResponse.setMessage("品牌保存成功");
            }
        } catch (Exception e) {
            log.error("系统错误：名称为【{}】的品牌保存失败",rpcRequest.getRo().getName());
            rpcResponse.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
            rpcResponse.setMessage("品牌保存失败");
        }
        return rpcResponse;
    }

    @Override
    public RpcResponse<Boolean> hasExisted(@NotBlank(message = "查询的品牌名称不能为空") String brandName) {
        Boolean flag = brandService.selectByName(brandName) != null ? Boolean.TRUE : Boolean.FALSE;
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "成功",flag);
    }

    @Override
    public RpcResponse<BrandVo> getById(@NotNull(message = "品牌Id不能为空") Long id) {
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS,"成功",mapper.map(brandService.selectById(id),BrandVo.class));
    }



    @Override
    public RpcResponse<Void> update(@NotNull(message = "品牌对象不能为空") RpcRequest<UpdateBrandRo> rpcRequest) {
        RpcResponse<Void> rpcResponse = new RpcResponse<>();
         try {
            if (brandService.update(mapper.map(rpcRequest.getRo(), Brand.class)) == 1) {
                rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
                rpcResponse.setMessage("品牌修改成功");
            }
        } catch (Exception e) {
             log.error("系统错误：名称为【{}】的品牌保存失败",rpcRequest.getRo().getName());
             rpcResponse.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
             rpcResponse.setMessage("品牌修改失败,系统错误");
        }
        return rpcResponse;
    }

    @Override
    public RpcResponse<Void> batchDeleteById(@NotNull(message = "品牌ID不能为空") RpcRequest<List<Long>> rpcRequest) {
        RpcResponse<Void> rpcResponse = new RpcResponse<>();
        try {
            brandService.batchDeleteById(rpcRequest.getRo());
            rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
            rpcResponse.setMessage("品牌删除成功");
        } catch (Exception e) {
            log.error("品牌id是{}删除失败",rpcRequest.getRo());
            rpcResponse.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
            rpcResponse.setMessage("品牌删除失败，系统错误");
        }
        return rpcResponse;
    }

    @Override
    public RpcResponse<List<BrandOptionVo>> getBrandList() {
        List<BrandOptionVo> brandVoList = brandService.list().stream()
                .map(brand -> new BrandOptionVo(brand.getId(), brand.getName()))
                .collect(Collectors.toList());
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "查询成功", brandVoList);
    }
}
