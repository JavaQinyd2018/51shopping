package com.qinyadong.shopping.service;

import com.qinyadong.shopping.base.BaseService;
import com.qinyadong.shopping.entity.SpecOption;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
public interface SpecOptionService extends BaseService<SpecOption> {

    /**
     * 根据specId获取规格项
     * @param specId
     * @return
     */
    List<SpecOption> selectBySpecId(Long specId);


    /**
     * 根据规格ID和规格项名称搜规格项
     * @param specId
     * @param optionName
     * @return
     */
    SpecOption selectBySpecIdAndOptionName(Long specId, String optionName);

    /**
     * 规格Id和规格项名称删除
     * @param optionName
     * @param specId
     * @return
     */
    Integer deleteByNameAndSpecId(String optionName, Long specId);

    /**
     * 通过specID删除
     * @param specId
     * @return
     */
    Integer deleteBySpecId(Long specId);
}
