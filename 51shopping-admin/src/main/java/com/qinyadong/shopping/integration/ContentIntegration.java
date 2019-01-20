package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.ro.AddContentRo;
import com.qinyadong.shopping.ro.SearchContentRo;
import com.qinyadong.shopping.ro.UpdateContentRo;
import com.qinyadong.shopping.vo.ContentVo;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
public interface ContentIntegration {

    /**
     * 分页查询
     * @param pageRo
     * @return
     */
    PageResult<ContentVo> searchByPage(PageRo<SearchContentRo> pageRo);

    /**
     * 添加
     * @param addContentRo
     * @return
     */
    Result<Void> addContent(AddContentRo addContentRo);

    /**
     * 修改
     * @param updateContentRo
     * @return
     */
    Result<Void> updateContent(UpdateContentRo updateContentRo);

    /**
     * ID查询
     * @param id
     * @return
     */
    Result<ContentVo> getById(Long id);

    /**
     * ID删除
     * @param idList
     * @return
     */
    Result<Void> batchDelete(List<Long> idList);
}
