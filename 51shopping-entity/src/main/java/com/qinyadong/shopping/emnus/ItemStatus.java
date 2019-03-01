package com.qinyadong.shopping.emnus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/28
 */
@AllArgsConstructor
@Getter
@ToString
public enum  ItemStatus {

    NORMAL(1,"正常状态，可销售状态"),

    OFF_SALE(2, "下架，不可销售状态"),

    DELETE(3,"删除状态");

    private Integer mark;
    private String description;
}
