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
     * 分页根据Pid查询
     * @param pageRo
     * @return
     */
    PageResult<ItemCategoryVo> searchByParentIdForPage(PageRo<SearchItemCategoryRo> pageRo);

    /**
     * 获取分类列表
     * @param parentId
     * @return
     */
    Result<List<ItemCategoryVo>> getListByParentId(Long parentId);
}
