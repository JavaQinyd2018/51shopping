package com.qinyadong.shopping.emnus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 * 上架类型
 */
@Getter
@AllArgsConstructor
public enum MarketableType {

    /**
     *
     */
    ON_SALE(1,"商品上架"),

    /**
     *
     */
    OFF_SALE(0, "商品下架");

    private Integer mark;
    private String description;
}
