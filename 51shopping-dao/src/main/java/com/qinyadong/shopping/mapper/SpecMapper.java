package com.qinyadong.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinyadong.shopping.entity.Specification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
public interface SpecMapper extends BaseMapper<Specification> {

    @Insert("insert into `pinyougoudb`.tb_specification(spec_name) values(#{specName})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int add(Specification specification);
}
