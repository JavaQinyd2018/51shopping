package com.qinyadong.shopping.manager;

import com.qinyadong.shopping.ro.AddGoodsInfoRo;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
public interface GoodsManager {

    /**
     * 添加商品信息
     * @param addGoodsInfoRo
     */
    void addGoodsInfo(AddGoodsInfoRo addGoodsInfoRo);
}
