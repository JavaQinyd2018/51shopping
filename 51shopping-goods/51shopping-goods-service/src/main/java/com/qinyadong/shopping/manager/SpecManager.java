package com.qinyadong.shopping.manager;

import com.qinyadong.shopping.ro.AddSpecInfoRo;
import com.qinyadong.shopping.ro.UpdateSpecInfoRo;
import com.qinyadong.shopping.vo.SpecInfoVo;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
public interface SpecManager {

    /**
     * 添加规格信息
     * @param specInfoRo
     * @return
     */
    void addSpecInfo(AddSpecInfoRo specInfoRo);

    /**
     * 根据Id查询规格信息
     * @param id
     * @return
     */
    SpecInfoVo getById(Long id);

    /**
     * 更新
     * @param specInfoRo
     */
    void updateSpecInfo(UpdateSpecInfoRo specInfoRo);

    /**
     * 批量删除规格信息
     * @param idList
     */
    void deleteSpecInfo(List<Long> idList);
}
