package com.qinyadong.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.qinyadong.shopping.base.AbstractBaseServiceImpl;
import com.qinyadong.shopping.entity.TypeTemplate;
import com.qinyadong.shopping.mapper.TypeTemplateMapper;
import com.qinyadong.shopping.ro.SearchTypeTemplateRo;
import com.qinyadong.shopping.service.TypeTemplateService;
import com.qinyadong.shopping.vo.TypeTemplateVo;
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
 * @Date: 2018/12/4
 */
@Service
@Slf4j
public class TypeTemplateServiceImpl extends AbstractBaseServiceImpl<TypeTemplate> implements TypeTemplateService {

    @Autowired
    private TypeTemplateMapper typeTemplateMapper;

    @Autowired
    private Mapper mapper;

    @Override
    public Map<String, Object> searchByPage(SearchTypeTemplateRo searchTypeTemplateRo, Integer pageNum, Integer pageSize) {
        Map<String, Object> pageInfo = Maps.newHashMap();
        QueryWrapper<TypeTemplate> queryWrapper = new QueryWrapper<>();
        if (searchTypeTemplateRo != null) {
            if (StringUtils.isNotBlank(searchTypeTemplateRo.getName())) {
                queryWrapper.lambda().eq(TypeTemplate::getName, searchTypeTemplateRo.getName());
            }
        }
        queryWrapper.orderByDesc("create_date");
        IPage<TypeTemplate> page = new Page<>(pageNum, pageSize);
        IPage<TypeTemplate> templateIPage = typeTemplateMapper.selectPage(page, queryWrapper);
        pageInfo.put("total",templateIPage.getTotal());
        pageInfo.put("typeTemplateVoList",templateIPage.getRecords().stream()
                .map(typeTemplate -> mapper.map(typeTemplate, TypeTemplateVo.class))
                .collect(Collectors.toList()));
        return pageInfo;
    }

    @Override
    public void deleteByIdList(List<Long> idList) {
        if (typeTemplateMapper.deleteBatchIds(idList) <= 0) {
            throw new RuntimeException("根据ID删除失败");
        }
    }
}
