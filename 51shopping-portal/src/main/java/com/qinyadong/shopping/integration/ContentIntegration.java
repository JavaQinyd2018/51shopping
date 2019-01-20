package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.vo.ContentVo;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
public interface ContentIntegration {

    /**
     * 根据类目查询
     * @param categoryId
     * @return
     */
    Result<List<ContentVo>> getCategoryId(Long categoryId);
}
