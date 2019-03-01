package com.qinyadong.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qinyadong.shopping.emnus.ItemStatus;
import com.qinyadong.shopping.entity.Item;
import com.qinyadong.shopping.mapper.ItemMapper;
import com.qinyadong.shopping.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2019/3/2
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> getAllItemList() {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Item::getStatus, ItemStatus.NORMAL);
        return itemMapper.selectList(queryWrapper);
    }
}
