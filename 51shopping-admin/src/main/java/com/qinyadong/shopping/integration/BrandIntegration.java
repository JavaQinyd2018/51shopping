package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.ro.BrandRo;
import com.qinyadong.shopping.ro.SearchBrandRo;
import com.qinyadong.shopping.ro.UpdateBrandRo;
import com.qinyadong.shopping.vo.BrandOptionVo;
import com.qinyadong.shopping.vo.BrandVo;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/6
 */
public interface BrandIntegration {

    /**
     * 查询
     * @return
     */
    Result<List<BrandOptionVo>> getBrandList();

    /**
     * 分页搜索
     * @param pageRo
     * @return
     */
    PageResult<BrandVo> searchByPage(PageRo<SearchBrandRo> pageRo);

    /**
     * 存在
     * @param brandName
     * @return
     */
    Result<Boolean> hasExisted(String brandName);

    /**
     *
     * @param brandRo
     * @return
     */
    Result<Void> add(BrandRo brandRo);

    /**
     * ID获取
     * @param id
     * @return
     */
    Result<BrandVo> getById(Long id);

    /**
     * 修改
     * @param updateBrandRo
     * @return
     */
    Result<Void> update(UpdateBrandRo updateBrandRo);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result<Void> batchDeleteById(List<Long> idList);
}
