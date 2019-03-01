package com.qinyadong.shopping.integration.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.ImportItemDataRpcService;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.BaseIntegration;
import com.qinyadong.shopping.integration.ImportItemDataIntegration;
import org.springframework.stereotype.Service;

/**
 * @Author: Yadong Qin
 * @Date: 2019/3/2
 */
@Service
public class ImportItemDataIntegrationImpl extends BaseIntegration implements ImportItemDataIntegration {

    @Reference
    private ImportItemDataRpcService importItemDataRpcService;

    @Override
    public Result<Void> importData() {
        return processResponse(importItemDataRpcService.importAllItemData());
    }
}
