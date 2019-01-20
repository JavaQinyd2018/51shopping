package com.qinyadong.shopping.aspect;

import com.alibaba.dubbo.rpc.RpcException;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.emnus.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/7
 * Rpc接口异常处理的切面
 */
@Aspect
@Component
@Slf4j
public class RpcExProcessAspect {

    @Pointcut("execution(public * com.qinyadong.shopping.integration.*.*(..))")
    public void interceptMethod() {
    }

    @Around("interceptMethod()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            return this.processException(proceedingJoinPoint, throwable);
        }
    }

    private <T> Result<T> processException(ProceedingJoinPoint proceedingJoinPoint, Throwable throwable) {
        Result<T> result = new Result<>();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        if (throwable instanceof RpcException) {
            String message = ((RpcException) throwable).getMessage();
            log.error("方法名称为：{}远程调用Rpc接口失败，错误信息：{}",methodName, throwable.getMessage());
            result.setMessage("远程请求失败:"+message);
            result.setSuccess(false);
            result.setStatus(ResultStatus.RPC_INVOCATION_FAIL);
        }else {
            log.error("无法处理的异常");
            result.setSuccess(false);
            result.setStatus(ResultStatus.SYSTEM_ERROR);
            result.setMessage("未知错误");
        }
        return result;
    }
}
