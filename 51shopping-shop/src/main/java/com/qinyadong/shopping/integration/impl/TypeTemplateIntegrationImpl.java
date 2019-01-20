package com.qinyadong.shopping.integration.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qinyadong.shopping.api.TypeTemplateRpcService;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.integration.BaseIntegration;
import com.qinyadong.shopping.integration.TypeTemplateIntegration;
import com.qinyadong.shopping.vo.TypeTemplateVo;
import org.springframework.stereotype.Service;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@Service
public class TypeTemplateIntegrationImpl extends BaseIntegration implements TypeTemplateIntegration {

    @Reference
    private TypeTemplateRpcService typeTemplateRpcService;

    @Override
    public Result<TypeTemplateVo> getById(Long id) {
        return processResponse(typeTemplateRpcService.getById(id));
    }
}
