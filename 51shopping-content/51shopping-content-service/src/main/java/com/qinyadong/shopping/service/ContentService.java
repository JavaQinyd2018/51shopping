package com.qinyadong.shopping.service;

import com.qinyadong.shopping.base.BaseService;
import com.qinyadong.shopping.entity.Content;
import com.qinyadong.shopping.ro.SearchContentRo;

import java.util.List;
import java.util.Map;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
public interface ContentService extends BaseService<Content> {

    /**
     * 分页查询
     * @param searchContentRo
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String, Object> searchByPage(SearchContentRo searchContentRo, Integer pageNum, Integer pageSize);

    /**
     * 根据类目ID查询
     * @param categoryId
     * @return
     */
    List<Content> getByCategoryId(Long categoryId);
}
