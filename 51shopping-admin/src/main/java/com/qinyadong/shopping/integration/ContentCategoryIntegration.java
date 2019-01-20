package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.ro.AddContentCategoryRo;
import com.qinyadong.shopping.ro.ContentCategoryVo;
import com.qinyadong.shopping.ro.SearchContentCategoryRo;
import com.qinyadong.shopping.ro.UpdateContentCategory;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/19
 */
public interface ContentCategoryIntegration {

    /**
     * 分页查询
     * @param pageRo
     * @return
     */
    PageResult<ContentCategoryVo> selectByPage(PageRo<SearchContentCategoryRo> pageRo);

    /**
     * 添加
     * @param addContentCategoryRo
     * @return
     */
    Result<Void> addContentCategory(AddContentCategoryRo addContentCategoryRo);

    /**
     * 更新
     * @param updateContentCategory
     * @return
     */
    Result<Void> updateContentCategory(UpdateContentCategory updateContentCategory);

    /**
     * 批量删除
     * @param idLongList
     * @return
     */
    Result<Void> batchDelete(List<Long> idLongList);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    Result<ContentCategoryVo> getById(Long id);

    /**
     * 查询所有
     * @return
     */
    Result<List<ContentCategoryVo>> list();
}
