package com.qinyadong.shopping.service;

import com.qinyadong.shopping.base.BaseService;
import com.qinyadong.shopping.entity.Specification;
import com.qinyadong.shopping.ro.SearchSpecRo;
import com.qinyadong.shopping.vo.SpecVo;

import java.util.List;
import java.util.Map;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
public interface SpecService extends BaseService<Specification> {

    /**
     * 分页搜索
     * @param searchSpecRo
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String, Object> searchByPage(SearchSpecRo searchSpecRo, Integer pageNum, Integer pageSize);

    /**
     * 添加规格并返回主键ID
     * @param specification
     * @return
     */
    Integer add(Specification specification);

    /**
     * 根据名称获取规格
     * @param specName
     * @return
     */
    Specification selectByName(String specName);

    /**
     * 删除
     * @param idList
     */
    Integer deleteByIdList(List<Long> idList);
}
