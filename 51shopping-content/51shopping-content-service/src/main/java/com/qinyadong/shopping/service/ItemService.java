package com.qinyadong.shopping.service;

import com.qinyadong.shopping.entity.Item;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2019/3/2
 */
public interface ItemService {

    /**
     * 获取所有的item信息
     * @return
     */
    List<Item> getAllItemList();
}
