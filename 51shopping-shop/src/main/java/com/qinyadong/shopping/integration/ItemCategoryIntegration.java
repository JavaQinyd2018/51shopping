package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.ro.SearchItemCategoryRo;
import com.qinyadong.shopping.vo.ItemCategoryVo;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/20
 */
public interface ItemCategoryIntegration {

    /**
     * 获取分类列表
     * @param parentId
     * @return
     */
    Result<List<ItemCategoryVo>> getListByParentId(Long parentId);

    /**
     *根据Id获取
     * @param id
     * @return
     */
    Result<ItemCategoryVo> getById(Long id);
}
