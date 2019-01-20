package com.qinyadong.shopping.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/28
 */
public abstract class AbstractBaseServiceImpl<T extends BaseEntity> implements BaseService<T>, InitializingBean {

    private T entity;

    @Autowired
    private BaseMapper<T> baseMapper;

    @SuppressWarnings("all")
    @Override
    public void afterPropertiesSet() throws Exception {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Class<T> clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
            entity = clazz.newInstance();
        }
    }

    @Override
    public int save(T entity) {
        return baseMapper.insert(entity);
    }

    @Override
    public int update(T entity) {
        return baseMapper.updateById(entity);
    }

    @Override
    public int deleteById(Serializable id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public T selectById(Serializable id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<T> list() {
        return baseMapper.selectList(null);
    }
}
