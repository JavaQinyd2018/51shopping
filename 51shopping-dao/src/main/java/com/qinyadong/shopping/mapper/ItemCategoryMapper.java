package com.qinyadong.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinyadong.shopping.entity.ItemCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/19
 */
public interface ItemCategoryMapper extends BaseMapper<ItemCategory> {
    /**
     * 查询
     * @param parentId
     * @return
     */
    @Select("select * from tb_item_category where parent_id = #{parentId}")
    List<ItemCategory> getListByParentId(@Param("parentId") Long parentId);
}
