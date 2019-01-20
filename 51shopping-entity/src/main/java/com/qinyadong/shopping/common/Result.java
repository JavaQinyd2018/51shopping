package com.qinyadong.shopping.common;

import com.qinyadong.shopping.emnus.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result<V> implements Serializable {

    private boolean success;
    private String message;
    private ResultStatus status;
    private V data;
}
