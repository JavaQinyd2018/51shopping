package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.ro.AddGoodsInfoRo;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
public interface GoodsIntegration {

    /**
     * 添加请求
     * @param addGoodsInfoRo
     * @return
     */
    Result<Void> addGoodsInfo(AddGoodsInfoRo addGoodsInfoRo);
}
