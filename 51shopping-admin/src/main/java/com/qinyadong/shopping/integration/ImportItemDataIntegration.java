package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.common.Result;

/**
 * @Author: Yadong Qin
 * @Date: 2019/3/2
 */
public interface ImportItemDataIntegration {

    /**
     * 导入数据
     * @return
     */
    Result<Void> importData();
}
