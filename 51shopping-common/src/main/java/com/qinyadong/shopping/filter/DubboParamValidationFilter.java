package com.qinyadong.shopping.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.validation.Validation;
import com.alibaba.dubbo.validation.Validator;
import com.qinyadong.shopping.common.RpcCode;
import com.qinyadong.shopping.common.RpcResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
@Slf4j
@Activate(group = Constants.PROVIDER,order = 9999)
public class DubboParamValidationFilter implements Filter {

    @Setter
    private Validation validation;

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            if (validation != null && !invocation.getMethodName().startsWith("$")) {
                Validator validator = validation.getValidator(invoker.getUrl());
                if (validator != null) {
                    validator.validate(invocation.getMethodName(),invocation.getParameterTypes(), invocation.getArguments());
                }
            }
            return invoker.invoke(invocation);
        } catch (RpcException e) {
            throw e;
        }catch (Throwable throwable) {
            RpcResponse<Object> rpcResponse = this.processException(invoker, invocation, throwable);
            if (rpcResponse != null) {
                return new RpcResult(rpcResponse);
            }
            log.error("无法处理的异常");
            throw new RpcException(throwable);
        }
    }

    /**
     * 处理异常
     * @param invoker
     * @param invocation
     * @param throwable
     * @return
     */
    private <T> RpcResponse<T> processException(Invoker<?> invoker, Invocation invocation, Throwable throwable) {
        RpcResponse<T> rpcResponse = this.buildResponse(invoker, invocation);

        if (rpcResponse != null) {
            //常量校验
            if (throwable instanceof ConstraintViolationException) {
                ConstraintViolationException constraintViolationException = (ConstraintViolationException) throwable;
                rpcResponse.setCode(RpcCode.REQUEST_PARAM_ERROR);
                rpcResponse.setMessage(constraintViolationException.getConstraintViolations().iterator().next().getMessage());
                return rpcResponse;
            }
        }
        return rpcResponse;
    }

    /**
     * 构建RpcResponse
     * @param invoker
     * @param invocation
     * @return
     */
    @SuppressWarnings("all")
    private <T> RpcResponse<T> buildResponse(Invoker<?> invoker, Invocation invocation) {
        try {
            Method method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            Class<?> returnType = method.getReturnType();
            log.info("返回值类型为：{}",returnType);
            if (!RpcResponse.class.isAssignableFrom(returnType)) {
                log.warn("{} 返回的参数类型{}不是RpcResponse类型，",invoker.getUrl(), returnType);
                return null;
            }
            return (RpcResponse<T>) returnType.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            log.error("构建RPC响应失败",e);
            return null;
        }
    }

}
