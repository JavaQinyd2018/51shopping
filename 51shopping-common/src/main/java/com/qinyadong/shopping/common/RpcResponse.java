package com.qinyadong.shopping.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/4
 */
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class RpcResponse<T> implements Serializable {

    private RpcCode code;
    private String message;
    private T data;
}
