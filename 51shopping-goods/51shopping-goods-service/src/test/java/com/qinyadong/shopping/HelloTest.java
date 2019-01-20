package com.qinyadong.shopping;

import com.qinyadong.shopping.common.RpcResponse;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/9
 */
public class HelloTest {

    public static void main(String[] args) throws ClassNotFoundException {
/*        RpcResponse<Void> rpcResponse = new RpcResponse<>();
        System.out.println(RpcResponse.class.isAssignableFrom(rpcResponse.getClass()));
        System.out.println(RpcResponse.class);*/
        System.out.println(RpcResponse.class.isAssignableFrom(RpcResponse.class));
        Class<?> aClass = Class.forName("com.qinyadong.shopping.common.RpcResponse");
        System.out.println(RpcResponse.class.isAssignableFrom(RpcResponse.class));
    }
}
