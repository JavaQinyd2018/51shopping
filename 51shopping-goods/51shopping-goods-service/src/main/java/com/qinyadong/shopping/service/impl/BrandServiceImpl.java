package com.qinyadong.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.qinyadong.shopping.base.AbstractBaseServiceImpl;
import com.qinyadong.shopping.entity.Brand;
import com.qinyadong.shopping.mapper.BrandMapper;
import com.qinyadong.shopping.ro.SearchBrandRo;
import com.qinyadong.shopping.service.BrandService;
import com.qinyadong.shopping.vo.BrandVo;
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
 * @Date: 2018/11/28
 */
@Service
@Slf4j
public class BrandServiceImpl extends AbstractBaseServiceImpl<Brand> implements BrandService{

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private Mapper mapper;

    @Override
    public List<BrandVo> listByName(String name) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Brand::getName,name);
        return brandMapper.selectList(queryWrapper).stream().map(brand -> mapper.map(brand, BrandVo.class)).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> searchByPage(SearchBrandRo searchBrandRo, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if (searchBrandRo != null) {
            if (StringUtils.isNotBlank(searchBrandRo.getName())) {
                queryWrapper.lambda().eq(Brand::getName, searchBrandRo.getName());
            }

            if (StringUtils.isNotBlank(searchBrandRo.getFirstChar())) {
                queryWrapper.lambda().eq(Brand::getFirstChar, searchBrandRo.getFirstChar());
            }
        }
        queryWrapper.orderByDesc("create_date");
        IPage<Brand> iPage = new Page<>();
        iPage.setSize(pageSize);
        iPage.setCurrent(pageNum);
        IPage<Brand> brandPage = brandMapper.selectPage(iPage, queryWrapper);
        List<BrandVo> brandVoList = brandPage.getRecords().stream().map(brand -> mapper.map(brand, BrandVo.class))
                .collect(Collectors.toList());
        map.put("total",brandPage.getTotal());
        map.put("brandVoList",brandVoList);
        return map;
    }

    @Override
    public Brand selectByName(String brandName) {
        return brandMapper.selectByName(brandName);
    }

    @Override
    public void batchDeleteById(List<Long> idList) {
        brandMapper.deleteBatchIds(idList);
    }
}
