package com.qinyadong.shopping.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.qinyadong.shopping.api.ContentRpcService;
import com.qinyadong.shopping.cache.CacheHelper;
import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.entity.Content;
import com.qinyadong.shopping.ro.AddContentRo;
import com.qinyadong.shopping.ro.SearchContentRo;
import com.qinyadong.shopping.ro.UpdateContentRo;
import com.qinyadong.shopping.service.ContentService;
import com.qinyadong.shopping.vo.ContentVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
@Service
@Slf4j
public class ContentRpcServiceImpl implements ContentRpcService {

    @Autowired
    private ContentService contentService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static String redisKey = CacheHelper.assembleRedisKey("content", "ContentService");

    @Override
    public RpcResponse<PageResult<ContentVo>> searchByPage(@NotNull(message = "广告查询对象不为空") RpcRequest<PageRo<SearchContentRo>> rpcRequest) {
        Map<String, Object> searchByPage = contentService.searchByPage(rpcRequest.getRo().getSearchRo(), rpcRequest.getRo().getPageNum(), rpcRequest.getRo().getPageSize());
        PageResult<ContentVo> pageResult = new PageResult<>((Long)searchByPage.get("total"),(List<ContentVo>)searchByPage.get("voList"));
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "查询成功", pageResult);
    }

    @Override
    public RpcResponse<Void> addContent(@NotNull(message = "添加广告对象不为空") RpcRequest<AddContentRo> rpcRequest) {
        RpcResponse<Void> response = new RpcResponse<>();
        try {
            if (contentService.save(mapper.map(rpcRequest.getRo(), Content.class)) == 1) {
                response.setCode(RpcCode.RESPONSE_SUCCESS);
                response.setMessage("广告内容添加成功");
                //添加成功了，清除所有与之相关的缓存
                Long categoryId = rpcRequest.getRo().getCategoryId();
                try {
                    stringRedisTemplate.opsForHash().delete(redisKey, categoryId+ "");
                } catch (Exception e) {
                    log.error("清除redis缓存失败，类目ID为：{}，错误信息为：{}",categoryId, e);
                }
            }
        } catch (Exception e) {
            log.error("添加失败，错误是：{}",e);
            response.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
            response.setMessage("广告内容添加失败，系统错误");
        }
        return response;
    }

    @Override
    public RpcResponse<Void> updateContent(@NotNull(message = "更新广告对象不为空") RpcRequest<UpdateContentRo> rpcRequest) {
        RpcResponse<Void> response = new RpcResponse<>();
        try {
            if (contentService.update(mapper.map(rpcRequest.getRo(), Content.class)) == 1) {
                response.setCode(RpcCode.RESPONSE_SUCCESS);
                response.setMessage("广告内容修改成功");
                //添加成功了，清除所有与之相关的缓存
                Long categoryId = rpcRequest.getRo().getCategoryId();
                try {
                    stringRedisTemplate.opsForHash().delete(redisKey, categoryId+ "");
                } catch (Exception e) {
                    log.error("清除redis缓存失败，类目ID为：{}，错误信息为：{}",categoryId, e);
                }
            }
        } catch (Exception e) {
            log.error("添加失败，错误是：{}",e);
            response.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
            response.setMessage("广告内容修改失败，系统错误");
        }
        return response;
    }

    @Override
    public RpcResponse<ContentVo> getById(@NotNull(message = "Id请求对象不能为空") RpcRequest<Long> request) {
         return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "查询成功",mapper.map(contentService.selectById(request.getRo()), ContentVo.class));
    }

    @Override
    public RpcResponse<Void> batchDelete(@NotNull(message = "删除请求对象不能为空") RpcRequest<List<Long>> rpcRequest) {
        RpcResponse<Void> response = new RpcResponse<>();
        try {
            if (contentService.deleteByIdList(rpcRequest.getRo()) > 0) {
                response.setCode(RpcCode.RESPONSE_SUCCESS);
                response.setMessage("广告内容删除成功");
                List<Long> idList = rpcRequest.getRo();
                //遍历id删除所有categoryId对应的缓存
                idList.forEach(id -> {
                    Content content = contentService.selectById(id);
                    if (content == null) {
                        return;
                    }
                    try {
                        stringRedisTemplate.opsForHash().delete(redisKey, content.getCategoryId() +"");
                    } catch (Exception e) {
                        log.error("清除redis缓存失败，类目ID为：{}，错误信息为：{}",content.getCategoryId(), e);
                    }
                });
            }
        } catch (Exception e) {
            log.error("添加失败，错误是：{}",e);
            response.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
            response.setMessage("广告内容删除失败，系统错误");
        }
        return response;
    }

    @Override
    public RpcResponse<List<ContentVo>> getByCategoryId(@NotNull(message = "查询请求对象不能为空") RpcRequest<Long> rpcRequest) {
        List<ContentVo> contentVoList = null;
        Long categoryId = rpcRequest.getRo();
        try {
            contentVoList = JSONArray.parseArray((String) stringRedisTemplate.opsForHash().get(redisKey, categoryId + ""), ContentVo.class);
        } catch (Exception e) {
            log.error("从redis中获取数据失败,redisKey为：{}，错误信息为：{}",redisKey+"=="+categoryId,e);
        }

        if (CollectionUtils.isEmpty(contentVoList)) {
            contentVoList = contentService.getByCategoryId(categoryId)
                    .stream().map(content -> mapper.map(content, ContentVo.class))
                    .collect(Collectors.toList());
            try {
                stringRedisTemplate.opsForHash().put(redisKey, categoryId+"", JSON.toJSONString(contentVoList));
                stringRedisTemplate.expire(redisKey, 60, TimeUnit.MINUTES);
            } catch (Exception e) {
                log.error("redis中保存数据失败,redisKey为：{}，错误信息为：{}",redisKey+"=="+categoryId,e);
            }
        }
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "查询成功",contentVoList);
    }
}
