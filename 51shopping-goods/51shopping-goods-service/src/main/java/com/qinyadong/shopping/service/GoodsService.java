package com.qinyadong.shopping.service;

import com.qinyadong.shopping.base.BaseService;
import com.qinyadong.shopping.entity.Goods;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
public interface GoodsService extends BaseService<Goods> {

    /**
     * 保存商品并返回主键
     * @param goods
     * @return
     */
    int saveGoods(Goods goods);
}
