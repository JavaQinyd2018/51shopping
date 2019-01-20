package com.qinyadong.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.qinyadong.shopping.base.AbstractBaseServiceImpl;
import com.qinyadong.shopping.entity.Content;
import com.qinyadong.shopping.mapper.ContentMapper;
import com.qinyadong.shopping.ro.SearchContentRo;
import com.qinyadong.shopping.service.ContentService;
import com.qinyadong.shopping.vo.ContentVo;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
@Service
public class ContentServiceImpl extends AbstractBaseServiceImpl<Content> implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private Mapper mapper;

    @Override
    public Map<String, Object> searchByPage(SearchContentRo searchContentRo, Integer pageNum, Integer pageSize) {
        Map<String, Object> pageMap = Maps.newHashMap();
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        if (searchContentRo != null) {
            if (searchContentRo.getCategoryId() != null) {
                queryWrapper.lambda().eq(Content::getCategoryId, searchContentRo.getCategoryId());
            }

            if (StringUtils.isNotBlank(searchContentRo.getTitle())) {
                queryWrapper.lambda().like(Content::getTitle, searchContentRo.getTitle());
            }
        }
        queryWrapper.orderByDesc("create_date");
        IPage<Content> page = new Page<>(pageNum, pageSize);
        IPage<Content> contentIPage = contentMapper.selectPage(page, queryWrapper);
        pageMap.put("total",contentIPage.getTotal());
        pageMap.put("voList",contentIPage.getRecords().stream().map(content -> mapper.map(content, ContentVo.class)).collect(Collectors.toList()));
        return pageMap;
    }

    @Override
    public List<Content> getByCategoryId(Long categoryId) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Content::getCategoryId, categoryId);
        return contentMapper.selectList(queryWrapper);
    }
}
