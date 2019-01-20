package com.qinyadong.shopping.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.qinyadong.shopping.api.ContentCategoryRpcService;
import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.entity.ContentCategory;
import com.qinyadong.shopping.ro.AddContentCategoryRo;
import com.qinyadong.shopping.ro.ContentCategoryVo;
import com.qinyadong.shopping.ro.SearchContentCategoryRo;
import com.qinyadong.shopping.ro.UpdateContentCategory;
import com.qinyadong.shopping.service.ContentCategoryService;
import com.qinyadong.shopping.vo.ContentVo;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/19
 */
@Service
@Slf4j
public class ContentCategoryRpcServiceImpl implements ContentCategoryRpcService {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @Autowired
    private Mapper mapper;

    @Override
    public RpcResponse<PageResult<ContentCategoryVo>> selectByPage(@NotNull(message = "分页请求对象不能为空") RpcRequest<PageRo<SearchContentCategoryRo>> request) {
        Map<String, Object> map = contentCategoryService.selectByPage(request.getRo().getSearchRo(), request.getRo().getPageNum(), request.getRo().getPageSize());
        PageResult<ContentCategoryVo> pageResult = new PageResult<>((Long) map.get("total"),(List<ContentCategoryVo>)map.get("voList"));
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "查询成功",pageResult);
    }

    @Override
    public RpcResponse<Void> addContentCategory(@NotNull(message = "添加请求对象不能为空") RpcRequest<AddContentCategoryRo> rpcRequest) {
        RpcResponse<Void> rpcResponse = new RpcResponse<>();
        try {
            if (contentCategoryService.save(mapper.map(rpcRequest.getRo(), ContentCategory.class)) == 1) {
                rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
                rpcResponse.setMessage("内容类目添加成功");
            }
        } catch (Exception e) {
            log.error("内容目录添加失败，错误为：{}",e);
            rpcResponse.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
            rpcResponse.setMessage("内容目录添加失败,系统错误");
        }
        return rpcResponse;
    }

    @Override
    public RpcResponse<Void> updateContentCategory(@NotNull(message = "修改请求对象不能为空") RpcRequest<UpdateContentCategory> rpcRequest) {
        RpcResponse<Void> rpcResponse = new RpcResponse<>();
        try {
            if (contentCategoryService.update(mapper.map(rpcRequest.getRo(), ContentCategory.class)) == 1) {
                rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
                rpcResponse.setMessage("内容类目修改成功");
            }
        } catch (Exception e) {
            log.error("内容目录添加失败，错误为：{}",e);
            rpcResponse.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
            rpcResponse.setMessage("内容目录修改失败,系统错误");
        }
        return rpcResponse;
    }

    @Override
    public RpcResponse<Void> batchDelete(@NotNull(message = "修改请求对象不能为空") RpcRequest<List<Long>> rpcRequest) {
        RpcResponse<Void> rpcResponse = new RpcResponse<>();
        try {
            if (contentCategoryService.deleteByIdList(rpcRequest.getRo()) > 0) {
                rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
                rpcResponse.setMessage("内容类目删除成功");
            }
        } catch (Exception e) {
            log.error("内容目录删除失败，错误为：{}",e);
            rpcResponse.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
            rpcResponse.setMessage("内容目录删除失败,系统错误");
        }
        return rpcResponse;
    }

    @Override
    public RpcResponse<ContentCategoryVo> getById(@NotNull(message = "根据ID获取请求对象不能为空") RpcRequest<Long> rpcRequest) {
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "查询成功",mapper.map(contentCategoryService.selectById(rpcRequest.getRo()), ContentCategoryVo.class));
    }

    @Override
    public RpcResponse<List<ContentCategoryVo>> list() {
        List<ContentCategoryVo> contentCategoryVoList = contentCategoryService.list().stream()
                .map(contentCategory -> mapper.map(contentCategory, ContentCategoryVo.class))
                .collect(Collectors.toList());
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "查询成功",contentCategoryVoList);
    }
}
