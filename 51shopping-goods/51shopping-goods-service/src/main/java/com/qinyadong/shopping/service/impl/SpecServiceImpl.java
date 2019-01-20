package com.qinyadong.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.qinyadong.shopping.base.AbstractBaseServiceImpl;
import com.qinyadong.shopping.entity.Specification;
import com.qinyadong.shopping.mapper.SpecMapper;
import com.qinyadong.shopping.ro.SearchSpecRo;
import com.qinyadong.shopping.service.SpecService;
import com.qinyadong.shopping.vo.SpecVo;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
@Service
public class SpecServiceImpl extends AbstractBaseServiceImpl<Specification> implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private Mapper mapper;

    @Override
    public Map<String, Object> searchByPage(SearchSpecRo searchSpecRo, Integer pageNum, Integer pageSize) {
        Map<String, Object> pageInfo = Maps.newHashMap();
        QueryWrapper<Specification> queryWrapper = new QueryWrapper<>();
        if (searchSpecRo != null) {
            if (StringUtils.isNotBlank(searchSpecRo.getSpecName())) {
                queryWrapper.lambda().eq(Specification::getSpecName, searchSpecRo.getSpecName());
            }
        }
        queryWrapper.orderByDesc("create_date");
        IPage<Specification> page = new Page<>(pageNum, pageSize);
        IPage<Specification> selectPage = specMapper.selectPage(page, queryWrapper);
        pageInfo.put("total",selectPage.getTotal());
        pageInfo.put("specVoList",selectPage.getRecords().stream()
                .map(specification -> mapper.map(specification, SpecVo.class))
                .collect(Collectors.toList()));
        return pageInfo;
    }

    @Override
    public Specification selectByName(String specName) {
        QueryWrapper<Specification> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Specification::getSpecName, specName);
        return specMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer add(Specification specification) {
        return specMapper.add(specification);
    }

    @Override
    public Integer deleteByIdList(List<Long> idList) {
        return specMapper.deleteBatchIds(idList);
    }
}
