package com.qinyadong.shopping.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/3
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    private String code;
    private String message;
}
