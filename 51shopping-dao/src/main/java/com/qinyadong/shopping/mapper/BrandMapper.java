package com.qinyadong.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinyadong.shopping.entity.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/28
 */
public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Select("select * from `tb_brand` where name = #{name}")
    Brand selectByName(@Param(value = "name") String name);
}
