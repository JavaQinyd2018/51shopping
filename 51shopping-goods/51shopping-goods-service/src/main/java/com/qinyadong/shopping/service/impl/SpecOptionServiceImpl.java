package com.qinyadong.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qinyadong.shopping.base.AbstractBaseServiceImpl;
import com.qinyadong.shopping.entity.SpecOption;
import com.qinyadong.shopping.mapper.SpecOptionMapper;
import com.qinyadong.shopping.service.SpecOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
@Service
public class SpecOptionServiceImpl extends AbstractBaseServiceImpl<SpecOption> implements SpecOptionService {

    @Autowired
    private SpecOptionMapper specOptionMapper;

    @Override
    public List<SpecOption> selectBySpecId(Long specId) {
        QueryWrapper<SpecOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SpecOption::getSpecId, specId);
        return specOptionMapper.selectList(queryWrapper);
    }

    @Override
    public SpecOption selectBySpecIdAndOptionName(Long specId, String optionName) {
        QueryWrapper<SpecOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SpecOption::getSpecId, specId);
        queryWrapper.lambda().eq(SpecOption::getOptionName, optionName);
        return specOptionMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer deleteByNameAndSpecId(String optionName, Long specId) {
        QueryWrapper<SpecOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SpecOption::getSpecId, specId);
        queryWrapper.lambda().eq(SpecOption::getOptionName, optionName);
        return specOptionMapper.delete(queryWrapper);
    }

    @Override
    public Integer deleteBySpecId(Long specId) {
        QueryWrapper<SpecOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SpecOption::getSpecId, specId);
        return specOptionMapper.delete(queryWrapper);
    }
}
