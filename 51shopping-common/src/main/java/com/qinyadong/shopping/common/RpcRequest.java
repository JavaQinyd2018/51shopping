package com.qinyadong.shopping.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/6
 */
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class RpcRequest<R> implements Serializable {

    @Valid
    private R ro;
}
