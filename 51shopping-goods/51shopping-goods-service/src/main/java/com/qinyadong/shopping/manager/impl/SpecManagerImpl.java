package com.qinyadong.shopping.manager.impl;

import com.qinyadong.shopping.entity.SpecOption;
import com.qinyadong.shopping.entity.Specification;
import com.qinyadong.shopping.log.SystemLog;
import com.qinyadong.shopping.manager.SpecManager;
import com.qinyadong.shopping.ro.AddSpecInfoRo;
import com.qinyadong.shopping.ro.UpdateSpecInfoRo;
import com.qinyadong.shopping.service.SpecOptionService;
import com.qinyadong.shopping.service.SpecService;
import com.qinyadong.shopping.vo.SpecInfoVo;
import com.qinyadong.shopping.vo.SpecOptionVo;
import com.qinyadong.shopping.vo.SpecVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
@Service
@Slf4j
public class SpecManagerImpl implements SpecManager {

    @Autowired
    private SpecService specService;

    @Autowired
    private SpecOptionService specOptionService;

    @Autowired
    private Mapper mapper;

    @Override
    @SystemLog
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public void addSpecInfo(AddSpecInfoRo specInfoRo) {

        Specification specification = mapper.map(specInfoRo.getSpecification(), Specification.class);
        if (specService.add(specification) != 1) { ;
            throw new RuntimeException("数据库保存失败");
        }

        List<SpecOption> specOptionList = specInfoRo.getSpecificationOptionList().stream()
                .map(addSpecOptionRo ->{
                    SpecOption specOption = mapper.map(addSpecOptionRo, SpecOption.class);
                    specOption.setSpecId(specification.getId());
                    return specOption;
                }).collect(Collectors.toList());

            specOptionList.forEach(specOption -> {
                specOptionService.save(specOption);
            });
    }

    @Override
    public SpecInfoVo getById(Long id) {
        SpecInfoVo specInfoVo = new SpecInfoVo();
        specInfoVo.setSpecification(mapper.map(specService.selectById(id),SpecVo.class));
        specInfoVo.setSpecificationOptionList(specOptionService.selectBySpecId(id).stream()
                .map(specOption -> mapper.map(specOption, SpecOptionVo.class))
                .collect(Collectors.toList()));
        return specInfoVo;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class} )
    public void updateSpecInfo(UpdateSpecInfoRo specInfoRo) {

        if (specService.update(mapper.map(specInfoRo.getSpecification(), Specification.class)) != 1) {
            throw new RuntimeException("规格数据更新失败");
        }

        //1.从数据中获取所有改规格的规格名称
        List<String> oldSpecOptionNameList = specOptionService.selectBySpecId(specInfoRo.getSpecification().getId()).stream()
                .map(SpecOption::getOptionName)
                .collect(Collectors.toList());
        oldSpecOptionNameList.forEach(oldSpecOptionName -> {
            specInfoRo.getSpecificationOptionList().forEach(updateSpecOptionRo -> {
                if (!StringUtils.equals(oldSpecOptionName,updateSpecOptionRo.getOptionName())) {
                    if (specOptionService.deleteByNameAndSpecId(oldSpecOptionName, specInfoRo.getSpecification().getId()) != 1) {
                        throw new RuntimeException("规格项删除失败");
                    }
                }
            });
        });

        //2.修改数据
        List<SpecOption> specOptionList = specInfoRo.getSpecificationOptionList().stream()
                .map(updateSpecOptionRo -> mapper.map(updateSpecOptionRo, SpecOption.class))
                .collect(Collectors.toList());

        specOptionList.forEach(specOption -> {
            //一。数据库请求里面的
            SpecOption oldSpecOption = specOptionService.selectBySpecIdAndOptionName(specOption.getSpecId(), specOption.getOptionName());
            //1.如果之前没有该规格项。直接插入
            if (oldSpecOption == null) {
                if (specOptionService.save(specOption) != 1) {
                    throw new RuntimeException("规格项保存失败");
                }
            }else {
            //2.如果已经存在，修改信息
                if (specOptionService.update(specOption) != 1) {
                    throw new RuntimeException("规格项修改失败");
                }
            }

        });
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class} )
    public void deleteSpecInfo(List<Long> idList) {
        idList.forEach(id -> {
            if (specService.deleteById(id) != 1) {
                throw new RuntimeException("规格删除失败");
            }
            //如果没有对应的option
            if (specOptionService.deleteBySpecId(id) < 0) {
                throw new RuntimeException("规格项删除失败");
            }
        });
    }
}
