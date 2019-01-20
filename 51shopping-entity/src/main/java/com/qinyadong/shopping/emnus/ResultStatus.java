package com.qinyadong.shopping.emnus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/30
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultStatus {

    /**
     * 成功
     */
    SUCCESS(200,"结果成功"),
    /**
     * 请求错误
     */
    REQUEST_ILLEGALITY(400,"请求不合法"),

    /**
     *
     */
    REQUEST_PARAM_ERROR(405,"请求参数错误"),

    /**
     *
     */
    RPC_INVOCATION_FAIL(600, "远程调用失败"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(500,"系统错误");

    private Integer code;
    private String description;
}
