package com.qinyadong.shopping.api;

import com.qinyadong.shopping.common.RpcResponse;

/**
 * @Author: Yadong Qin
 * @Date: 2019/3/2
 */
public interface ImportItemDataRpcService {

    /**
     * 导入所有Item数据到ES
     * @return
     */
    RpcResponse<Void> importAllItemData();
}
