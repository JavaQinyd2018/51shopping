package com.qinyadong.shopping.manager.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qinyadong.shopping.emnus.AuditStatus;
import com.qinyadong.shopping.emnus.ItemStatus;
import com.qinyadong.shopping.entity.*;
import com.qinyadong.shopping.manager.GoodsManager;
import com.qinyadong.shopping.mapper.BrandMapper;
import com.qinyadong.shopping.mapper.ItemCategoryMapper;
import com.qinyadong.shopping.mapper.SellerMapper;
import com.qinyadong.shopping.ro.AddGoodsDescRo;
import com.qinyadong.shopping.ro.AddGoodsInfoRo;
import com.qinyadong.shopping.ro.AddGoodsRo;
import com.qinyadong.shopping.ro.AddItemRo;
import com.qinyadong.shopping.service.GoodsDescService;
import com.qinyadong.shopping.service.GoodsService;
import com.qinyadong.shopping.service.ItemService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ItemCategoryMapper itemCategoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGoodsInfo(AddGoodsInfoRo addGoodsInfoRo) {
        AddGoodsRo goodsRo = addGoodsInfoRo.getGoods();
        goodsRo.setAuditStatus(AuditStatus.NOT_AUDIT);
        Goods goods = mapper.map(goodsRo, Goods.class);
        goods.setCreateDate(new Date());
        goods.setUpdateDate(new Date());
        if (goodsService.saveGoods(goods) != 1) {
            throw new RuntimeException("添加商品失败");
        }

        if (goods.getId() == null) {
            throw new RuntimeException("返回主键失败");
        }
        AddGoodsDescRo goodsDescRo = addGoodsInfoRo.getGoodsDesc();
        goodsDescRo.setGoodsId(goods.getId());
        if (goodsDescService.save(mapper.map(goodsDescRo, GoodsDesc.class)) != 1) {
            throw new RuntimeException("保存商品信息详情失败");
        }

        List<AddItemRo> itemList = addGoodsInfoRo.getItemList();
        if (CollectionUtils.isNotEmpty(itemList)) {
            itemList.forEach(addItemRo -> {
                Item item = mapper.map(addItemRo, Item.class);
                StringBuilder stringBuilder = new StringBuilder();
                if (StringUtils.isNotBlank(item.getSpec())) {
                    Map<String, Object> map = JSONObject.parseObject(item.getSpec());
                    stringBuilder.append(goods.getGoodsName());
                    for (String key : map.keySet()) {
                        stringBuilder.append(" ");
                        stringBuilder.append(map.get(key));
                    }
                }
                item.setTitle(stringBuilder.toString());
                item.setStatus(ItemStatus.NORMAL);
                item.setGoodsId(goods.getId());
                item.setSellerId(goods.getSellerId());
                item.setCategoryId(goods.getCategory3Id());
                //类目
                ItemCategory itemCategory = itemCategoryMapper.selectById(goods.getCategory3Id());
                item.setCategory(itemCategory != null ? itemCategory.getName() : null);
                //品牌
                Brand brand = brandMapper.selectById(goods.getBrandId());
                item.setBrand(brand != null ? brand.getName() : null);
                //商家
                Seller seller = sellerMapper.selectById(goods.getSellerId());
                item.setSeller(seller != null ? seller.getName() : null);
                //图片
                List<Map> mapList = JSONArray.parseArray(goodsDescRo.getItemImages(), Map.class);
                if (CollectionUtils.isNotEmpty(mapList)) {
                    item.setImage((String) mapList.get(0).get("url"));
                }
                if (itemService.save(item) != 1) {
                    throw new RuntimeException("item添加失败");
                }
            });
        }
    }
}
