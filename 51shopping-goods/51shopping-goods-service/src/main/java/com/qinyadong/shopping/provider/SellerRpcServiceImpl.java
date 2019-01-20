package com.qinyadong.shopping.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.qinyadong.shopping.api.SellerRpcService;
import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.entity.Seller;
import com.qinyadong.shopping.ro.*;
import com.qinyadong.shopping.service.SellerService;
import com.qinyadong.shopping.vo.SellerVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
@Service
@Slf4j
public class SellerRpcServiceImpl implements SellerRpcService {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private Mapper mapper;

    @Override
    public RpcResponse<Void> addSeller(RpcRequest<AddSellerRo> rpcRequest) {
        RpcResponse<Void> rpcResponse = new RpcResponse<>();
        try {
            if (sellerService.save(mapper.map(rpcRequest.getRo(), Seller.class)) ==1) {
                rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
                rpcResponse.setMessage("商家添加成功");
            };
        } catch (Exception e) {
            log.error("商家添加失败，错误：{}",e);
            rpcResponse.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
            rpcResponse.setMessage("商家添加失败");
        }
        return rpcResponse;
    }

    @Override
    public RpcResponse<SellerVo> getBySellerId(@NotNull(message = "获取商家信息的对象不能为空") RpcRequest<String> rpcRequest) {
        SellerVo sellerVo = mapper.map(sellerService.getBySellerId(rpcRequest.getRo()), SellerVo.class);
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "查询成功", sellerVo);
    }

    @Override
    public RpcResponse<Void> updateSeller(RpcRequest<UpdateSellerRo> rpcRequest) {
        RpcResponse<Void> rpcResponse = new RpcResponse<>();
        try {
            if (sellerService.update(mapper.map(rpcRequest.getRo(), Seller.class)) ==1) {
                rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
                rpcResponse.setMessage("商家修改成功");
            }
        } catch (Exception e) {
            log.error("商家修改失败，错误：{}",e);
            rpcResponse.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
            rpcResponse.setMessage("商家修改失败");
        }
        return rpcResponse;
    }

    @Override
    public RpcResponse<String> getPasswordBySellerId(@NotNull(message = "获取请求的对象不能为空") RpcRequest<String> buildRequest) {
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "查询成功",sellerService.getPasswordBySellerId(buildRequest.getRo()));
    }

    @Override
    public RpcResponse<Void> updatePassword(@NotNull(message = "更新密码的对象不能为空") RpcRequest<UpdatePasswordRo> rpcRequest) {
        RpcResponse<Void> rpcResponse = new RpcResponse<>();
        try {
            if (sellerService.updatePassword(rpcRequest.getRo().getNewPassword(), rpcRequest.getRo().getSellerId()) ==1) {
                rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
                rpcResponse.setMessage("密码修改成功");
            }
        } catch (Exception e) {
            log.error("密码修改失败，错误：{}",e);
            rpcResponse.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
            rpcResponse.setMessage("密码修改失败");
        }
        return rpcResponse;
    }

    @Override
    @SuppressWarnings("all")
    public RpcResponse<PageResult<SellerVo>> searchByPage(@NotNull(message = "分页查询的对象不能为空") RpcRequest<PageRo<SearchSellerRo>> rpcRequest) {
        PageRo<SearchSellerRo> pageRo = rpcRequest.getRo();
        Map<String, Object> selectPage = sellerService.selectByPage(pageRo.getSearchRo(), pageRo.getPageNum(), pageRo.getPageSize());
        PageResult<SellerVo> pageResult = new PageResult<>((Long) selectPage.get("total"),(List<SellerVo>)selectPage.get("sellerVoList"));
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS,"查询成功",pageResult);
    }

    @Override
    public RpcResponse<Void> updateSellerStatus(@NotNull(message = "商家审核请求不能为空") RpcRequest<AuditSellerRo> rpcRequest) {
        RpcResponse<Void> rpcResponse = new RpcResponse<>();
        try {
            if (sellerService.auditSellerStatus(rpcRequest.getRo().getSellerId(), rpcRequest.getRo().getStatus()) == 1) {
                rpcResponse.setMessage("商家审核操作成功");
                rpcResponse.setCode(RpcCode.RESPONSE_SUCCESS);
            }
        } catch (Exception e) {
            rpcResponse.setMessage("商家审核操作失败");
            rpcResponse.setCode(RpcCode.SYSTEM_RESPONSE_EXCEPTION);
        }
        return rpcResponse;
    }

    @SuppressWarnings("all")
    @Override
    public RpcResponse<PageResult<SellerVo>> searchAllByPage(@NotNull(message = "分页查询的对象不能为空") RpcRequest<PageRo<SearchAllSellerRo>> rpcRequest) {
        PageRo<SearchAllSellerRo> pageRo = rpcRequest.getRo();
        Map<String, Object> selectPage = sellerService.selectAllByPage(pageRo.getSearchRo(), pageRo.getPageNum(), pageRo.getPageSize());
        PageResult<SellerVo> pageResult = new PageResult<>((Long) selectPage.get("total"),(List<SellerVo>)selectPage.get("sellerVoList"));
        return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS,"查询成功",pageResult);
    }
}
