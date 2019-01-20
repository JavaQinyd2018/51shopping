package com.qinyadong.shopping.service;

import com.qinyadong.shopping.base.BaseService;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.entity.Brand;
import com.qinyadong.shopping.ro.SearchBrandRo;
import com.qinyadong.shopping.vo.BrandVo;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/28
 */
public interface BrandService extends BaseService<Brand> {

    /**
     *
     * @param name
     * @return
     */
    List<BrandVo> listByName(String name);

    /**
     * 分页查询
     * @param searchBrandRo
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String,Object> searchByPage(SearchBrandRo searchBrandRo, Integer pageNum, Integer pageSize);

    /**
     * 查询
     * @param brandName
     * @return
     */
    Brand selectByName(String brandName);

    /**
     * 删除
     * @param idList
     */
    void batchDeleteById(List<Long> idList);
}
