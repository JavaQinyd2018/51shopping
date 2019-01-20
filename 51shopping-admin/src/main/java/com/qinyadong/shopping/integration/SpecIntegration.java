package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.common.*;
import com.qinyadong.shopping.ro.AddSpecInfoRo;
import com.qinyadong.shopping.ro.SearchSpecRo;
import com.qinyadong.shopping.ro.UpdateSpecInfoRo;
import com.qinyadong.shopping.vo.SpecInfoVo;
import com.qinyadong.shopping.vo.SpecVo;
import com.qinyadong.shopping.vo.SpecificationOptionVo;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/7
 */
public interface SpecIntegration {

    /**
     * 获取规格列表
     * @return
     */
    Result<List<SpecificationOptionVo>> getSpecList();

    /**
     * 分页搜索
     * @param pageRo
     * @return
     */
    PageResult<SpecVo> searchByPage(PageRo<SearchSpecRo> pageRo);

    /**
     * 是否存在
     * @param specName
     * @return
     */
    Boolean hasExisted(String specName);

    /**
     * 添加
     * @param addSpecInfoRo
     * @return
     */
    Result<Void> addSpecInfo(AddSpecInfoRo addSpecInfoRo);

    /**
     * ID获取
     * @param id
     * @return
     */
    Result<SpecInfoVo> getById(Long id);

    /**
     * 更新
     * @param updateSpecInfoRo
     * @return
     */
    Result<Void> updateSpecInfo(UpdateSpecInfoRo updateSpecInfoRo);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Result<Void> deleteByIdList(List<Long> idList);
}
