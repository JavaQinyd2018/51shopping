package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.vo.TypeTemplateVo;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
public interface TypeTemplateIntegration {

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    Result<TypeTemplateVo> getById(Long id);
}
