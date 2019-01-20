package com.qinyadong.shopping.service;

import com.qinyadong.shopping.base.BaseService;
import com.qinyadong.shopping.entity.ItemCategory;
import com.qinyadong.shopping.ro.SearchItemCategoryRo;

import java.util.List;
import java.util.Map;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/19
 */
public interface ItemCategoryService extends BaseService<ItemCategory> {

    /**
     * 分页
     * @param searchItemCategoryRo
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String,Object> searchByPage(SearchItemCategoryRo searchItemCategoryRo, Integer pageNum, Integer pageSize);

    /**
     * 获取类目list
     * @param parentId
     * @return
     */
    List<ItemCategory> getListByParentId(Long parentId);
}
