package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.check.ResponseCheckUtils;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.common.RpcRequest;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.emnus.ResultStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/6
 */
@Slf4j
public class BaseIntegration {

    /**
     * 构建rpc请求
     * @param r
     * @param <R>
     * @return
     */
    protected <R>RpcRequest<R> buildRequest(R r) {
        RpcRequest<R> request = new RpcRequest<>();
        request.setRo(r);
        return request;
    }

    /**
     * 处理rpc响应结果
     * @param response
     * @param <T>
     * @return
     */
    protected <T>Result<T> processResponse(RpcResponse<T> response) {
        Result<T> result = new Result<>();
        if (response != null) {
            if (ResponseCheckUtils.isSuccess(response.getCode())) {
                result.setStatus(ResultStatus.SUCCESS);
                result.setSuccess(true);
                result.setMessage(response.getMessage());
                result.setData(response.getData());
            }else if (ResponseCheckUtils.equals("00000001", response.getCode())) {
                result.setStatus(ResultStatus.REQUEST_PARAM_ERROR);
                result.setSuccess(false);
                result.setMessage(response.getMessage());
            }else if (ResponseCheckUtils.equals("00000002",response.getCode())) {
                result.setSuccess(false);
                result.setMessage(response.getMessage());
                result.setStatus(ResultStatus.RPC_INVOCATION_FAIL);
            }else {
                result.setSuccess(false);
                result.setMessage("无法处理的响应");
            }
        }
        return result;
    }
}
