package com.qinyadong.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.qinyadong.shopping.base.AbstractBaseServiceImpl;
import com.qinyadong.shopping.entity.ItemCategory;
import com.qinyadong.shopping.mapper.ItemCategoryMapper;
import com.qinyadong.shopping.ro.SearchItemCategoryRo;
import com.qinyadong.shopping.service.ItemCategoryService;
import com.qinyadong.shopping.vo.ItemCategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/19
 */
@Service
@Slf4j
public class ItemCategoryServiceImpl extends AbstractBaseServiceImpl<ItemCategory> implements ItemCategoryService {

    @Autowired
    private ItemCategoryMapper itemCategoryMapper;

    @Autowired
    private Mapper mapper;

    @Override
    public Map<String, Object> searchByPage(SearchItemCategoryRo searchItemCategoryRo, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        QueryWrapper<ItemCategory> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchItemCategoryRo.getName())) {
            queryWrapper.lambda().eq(ItemCategory::getName, searchItemCategoryRo.getName());
        }
        queryWrapper.lambda().eq(ItemCategory::getParentId,searchItemCategoryRo.getParentId());
        IPage<ItemCategory> page = new Page<>(pageNum, pageSize);
        IPage<ItemCategory> selectPage = itemCategoryMapper.selectPage(page, queryWrapper);
        map.put("total", selectPage.getTotal());
        map.put("ItemCategoryVoList", selectPage.getRecords().stream()
                .map(itemCategory -> mapper.map(itemCategory, ItemCategoryVo.class))
                .collect(Collectors.toList()));
        return map;
    }

    @Override
    public List<ItemCategory> getListByParentId(Long parentId) {
        return itemCategoryMapper.getListByParentId(parentId);
    }
}
