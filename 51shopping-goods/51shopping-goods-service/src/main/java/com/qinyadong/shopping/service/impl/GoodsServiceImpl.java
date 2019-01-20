package com.qinyadong.shopping.service.impl;

import com.qinyadong.shopping.base.AbstractBaseServiceImpl;
import com.qinyadong.shopping.entity.Goods;
import com.qinyadong.shopping.mapper.GoodsMapper;
import com.qinyadong.shopping.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@Service
public class GoodsServiceImpl extends AbstractBaseServiceImpl<Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int saveGoods(Goods goods) {
        return goodsMapper.save(goods);
    }
}
