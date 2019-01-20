package com.qinyadong.shopping.integration.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.GoodsRpcService;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.BaseIntegration;
import com.qinyadong.shopping.integration.GoodsIntegration;
import com.qinyadong.shopping.ro.AddGoodsInfoRo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@Service
public class GoodsIntegrationImpl extends BaseIntegration implements GoodsIntegration {

   @Reference
   private GoodsRpcService goodsRpcService;

    @Override
    public Result<Void> addGoodsInfo(AddGoodsInfoRo addGoodsInfoRo) {
        return processResponse(goodsRpcService.addGoodsInfo(buildRequest(addGoodsInfoRo)));
    }
}
