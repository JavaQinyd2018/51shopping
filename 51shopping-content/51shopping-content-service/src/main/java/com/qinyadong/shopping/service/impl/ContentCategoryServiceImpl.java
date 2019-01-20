package com.qinyadong.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.qinyadong.shopping.base.AbstractBaseServiceImpl;
import com.qinyadong.shopping.entity.ContentCategory;
import com.qinyadong.shopping.mapper.ContentCategoryMapper;
import com.qinyadong.shopping.ro.ContentCategoryVo;
import com.qinyadong.shopping.ro.SearchContentCategoryRo;
import com.qinyadong.shopping.service.ContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/19
 */
@Service
public class ContentCategoryServiceImpl extends AbstractBaseServiceImpl<ContentCategory> implements ContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Autowired
    private Mapper mapper;

    @Override
    public Map<String, Object> selectByPage(SearchContentCategoryRo searchContentCategoryRo, Integer pageNum, Integer pageSize) {
        Map<String, Object> pageResult = Maps.newHashMap();
        QueryWrapper<ContentCategory> queryWrapper = new QueryWrapper<>();
        if (searchContentCategoryRo != null) {
            if (StringUtils.isNotBlank(searchContentCategoryRo.getName())) {
                queryWrapper.lambda().eq(ContentCategory::getName, searchContentCategoryRo.getName());
            }
        }
        IPage<ContentCategory> page = new Page<>(pageNum, pageSize);
        IPage<ContentCategory> selectPage = contentCategoryMapper.selectPage(page, queryWrapper);
        pageResult.put("total", selectPage.getTotal());
        pageResult.put("voList",selectPage.getRecords().stream()
                .map(contentCategory -> mapper.map(contentCategory, ContentCategoryVo.class))
                .collect(Collectors.toList()));
        return pageResult;
    }
}
