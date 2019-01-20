package com.qinyadong.shopping.common;

import com.qinyadong.shopping.base.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageResult<T extends BaseVo> implements Serializable {

    private Long total;
    private List<T> list;
}
