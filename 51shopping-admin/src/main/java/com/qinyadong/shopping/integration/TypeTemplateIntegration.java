package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.ro.AddTypeTemplateRo;
import com.qinyadong.shopping.ro.SearchTypeTemplateRo;
import com.qinyadong.shopping.ro.UpdateTypeTemplateRo;
import com.qinyadong.shopping.vo.TypeTemplateVo;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/6
 */
public interface TypeTemplateIntegration {

    /**
     * 添加
     * @param addTypeTemplateRo
     * @return
     */
    Result<Void> add(AddTypeTemplateRo addTypeTemplateRo);

    /**
     * 分页查询
     * @param pageRo
     * @return
     */
    PageResult<TypeTemplateVo> searchByPage(PageRo<SearchTypeTemplateRo> pageRo);

    /**
     * Id获取
     * @param id
     * @return
     */
    Result<TypeTemplateVo> getById(Long id);

    /**
     * 更新
     * @param updateTypeTemplateRo
     * @return
     */
    Result<Void> update(UpdateTypeTemplateRo updateTypeTemplateRo);

    /**
     * 删除
     * @param idList
     * @return
     */
    Result<Void> deleteByIds(List<Long> idList);
}
