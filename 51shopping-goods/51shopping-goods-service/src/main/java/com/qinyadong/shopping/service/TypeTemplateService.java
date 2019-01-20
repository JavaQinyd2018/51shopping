package com.qinyadong.shopping.service;

import com.google.common.collect.Maps;
import com.qinyadong.shopping.base.BaseService;
import com.qinyadong.shopping.entity.TypeTemplate;
import com.qinyadong.shopping.ro.SearchTypeTemplateRo;

import java.util.List;
import java.util.Map;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/4
 */
public interface TypeTemplateService extends BaseService<TypeTemplate> {

    /**
     * 分页查询
     * @param searchTypeTemplateRo
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String, Object> searchByPage(SearchTypeTemplateRo searchTypeTemplateRo, Integer pageNum, Integer pageSize);

    /**
     * 删除
     * @param idList
     */
    void deleteByIdList(List<Long> idList);
}
