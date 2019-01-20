package com.qinyadong.shopping.base;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/28
 */

public interface BaseService<T extends BaseEntity> {

    int save(T entity);

    int update(T entity);

    int deleteById(Serializable id);

    T selectById(Serializable id);

    List<T> list();
}
