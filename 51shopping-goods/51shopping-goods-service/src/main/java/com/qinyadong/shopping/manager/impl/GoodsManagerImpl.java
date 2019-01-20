package com.qinyadong.shopping.manager.impl;

import com.qinyadong.shopping.emnus.AuditStatus;
import com.qinyadong.shopping.entity.Goods;
import com.qinyadong.shopping.entity.GoodsDesc;
import com.qinyadong.shopping.manager.GoodsManager;
import com.qinyadong.shopping.ro.AddGoodsDescRo;
import com.qinyadong.shopping.ro.AddGoodsInfoRo;
import com.qinyadong.shopping.ro.AddGoodsRo;
import com.qinyadong.shopping.service.GoodsDescService;
import com.qinyadong.shopping.service.GoodsService;
import com.qinyadong.shopping.service.ItemService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@Service
public class GoodsManagerImpl implements GoodsManager {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsDescService goodsDescService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private Mapper mapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGoodsInfo(AddGoodsInfoRo addGoodsInfoRo) {
        AddGoodsRo goodsRo = addGoodsInfoRo.getGoods();
        goodsRo.setAuditStatus(AuditStatus.NOT_AUDIT);
        Goods goods = mapper.map(goodsRo, Goods.class);
        if (goodsService.saveGoods(goods) != 1) {
            throw new RuntimeException("添加商品失败");
        }

        if (goods.getId() == null) {
            throw new RuntimeException("返回主键失败");
        }
        @Valid AddGoodsDescRo goodsDescRo = addGoodsInfoRo.getGoodsDesc();
        goodsDescRo.setGoodsId(goods.getId());
        if (goodsDescService.save(mapper.map(goodsDescRo, GoodsDesc.class)) != 1) {
            throw new RuntimeException("保存商品信息详情失败");
        }

    }
}
