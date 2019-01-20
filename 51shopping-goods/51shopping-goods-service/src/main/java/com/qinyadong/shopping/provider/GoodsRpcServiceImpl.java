package com.qinyadong.shopping.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.qinyadong.shopping.api.GoodsRpcService;
import com.qinyadong.shopping.common.RpcCode;
import com.qinyadong.shopping.common.RpcRequest;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.manager.GoodsManager;
import com.qinyadong.shopping.ro.AddGoodsInfoRo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@Service
@Slf4j
public class GoodsRpcServiceImpl implements GoodsRpcService {

    @Autowired
    private GoodsManager goodsManager;

    @Override
    public RpcResponse<Void> addGoodsInfo(@NotNull(message = "添加商品请求信息不能为空") RpcRequest<AddGoodsInfoRo> rpcRequest) {
        try {
            goodsManager.addGoodsInfo(rpcRequest.getRo());
            return new RpcResponse<>(RpcCode.RESPONSE_SUCCESS, "商品添加成功",null);
        } catch (Exception e) {
            log.error("系统错误：{}，",e);
            return new RpcResponse<>(RpcCode.SYSTEM_RESPONSE_EXCEPTION, "商品添加失败",null);
        }
    }
}
