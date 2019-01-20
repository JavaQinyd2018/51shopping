package com.qinyadong.shopping;

import com.qinyadong.shopping.api.ContentCategoryRpcService;
import com.qinyadong.shopping.common.RpcResponse;
import org.junit.Test;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/19
 */
public class DubboValidatorTest {

    @Test
    public void test() {
        Set<Method> allMethods = ReflectionUtils.getAllMethods(ContentCategoryRpcService.class);
        for (Method method : allMethods) {
            Class<?> returnType = method.getReturnType();
            System.out.println(!RpcResponse.class.isAssignableFrom(returnType));
        }
    }
}
