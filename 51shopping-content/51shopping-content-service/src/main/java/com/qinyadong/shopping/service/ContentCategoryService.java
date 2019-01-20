package com.qinyadong.shopping.service;

import com.qinyadong.shopping.base.BaseService;
import com.qinyadong.shopping.entity.ContentCategory;
import com.qinyadong.shopping.ro.SearchContentCategoryRo;

import java.util.Map;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/19
 */
public interface ContentCategoryService extends BaseService<ContentCategory> {

    /**
     * 分页查询
     * @param searchContentCategoryRo
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String, Object> selectByPage(SearchContentCategoryRo searchContentCategoryRo,
                                     Integer pageNum,
                                     Integer pageSize);
}
