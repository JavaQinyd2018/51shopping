package com.qinyadong.shopping.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/4
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public enum RpcCode {

    /**
     *
     */
    RESPONSE_SUCCESS("00000000","结果响应成功"),

    /**
     *
     */
    REQUEST_PARAM_ERROR("00000001","请求参数校验失败"),

    /**
     *
     */
    SYSTEM_RESPONSE_EXCEPTION("00000002","系统响应异常");


    private String code;
    private String description;
}
