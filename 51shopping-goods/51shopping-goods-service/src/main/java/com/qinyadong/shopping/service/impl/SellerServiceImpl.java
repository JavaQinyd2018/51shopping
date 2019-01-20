package com.qinyadong.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.qinyadong.shopping.base.AbstractBaseServiceImpl;
import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.emnus.AuditStatus;
import com.qinyadong.shopping.entity.Seller;
import com.qinyadong.shopping.mapper.SellerMapper;
import com.qinyadong.shopping.ro.SearchAllSellerRo;
import com.qinyadong.shopping.ro.SearchSellerRo;
import com.qinyadong.shopping.service.SellerService;
import com.qinyadong.shopping.vo.SellerVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
@Service
@Slf4j
public class SellerServiceImpl extends AbstractBaseServiceImpl<Seller> implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private Mapper mapper;

    @Override
    public Seller getBySellerId(String sellerId) {
        QueryWrapper<Seller> wrapper = new QueryWrapper<>();
        wrapper.eq("seller_id",sellerId);
        return sellerMapper.selectOne(wrapper);
    }

    @Override
    public String getPasswordBySellerId(String sellerId) {
        return sellerMapper.getPasswordBySellerId(sellerId);
    }

    @Override
    public int updatePassword(String newPassword, String sellerId) {
        return sellerMapper.updatePassword(newPassword, sellerId);
    }

    @Override
    public Map<String, Object> selectByPage(SearchSellerRo searchRo, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        if (searchRo != null) {
            if (StringUtils.isNotBlank(searchRo.getSellerId())) {
                queryWrapper.lambda().eq(Seller::getSellerId, searchRo.getSellerId());
            }

            if (StringUtils.isNotBlank(searchRo.getName())) {
                queryWrapper.lambda().eq(Seller::getName, searchRo.getName());
            }

            if (StringUtils.isNotBlank(searchRo.getNickName())) {
                queryWrapper.lambda().eq(Seller::getNickName, searchRo.getNickName());
            }
            if (StringUtils.isNotBlank(searchRo.getLinkmanName())) {
                queryWrapper.lambda().eq(Seller::getLinkmanName, searchRo.getLinkmanName());
            }
            queryWrapper.lambda().orderByDesc(Seller::getCreateDate);
            queryWrapper.lambda().eq(Seller::getStatus, AuditStatus.NOT_AUDIT);
            IPage<Seller> page = new Page<>(pageNum, pageSize);
            IPage<Seller> selectPage = sellerMapper.selectPage(page, queryWrapper);
            map.put("sellerVoList", selectPage.getRecords().stream().map(seller -> mapper.map(seller, SellerVo.class)).collect(Collectors.toList()));
            map.put("total", selectPage.getTotal());
        }
        return map;
    }

    @Override
    public int auditSellerStatus(String sellerId, AuditStatus status) {
        Seller seller = getBySellerId(sellerId);
        if (seller == null) {
            throw new RuntimeException("该商家ID对应的商家不存在");
        }
        if (!seller.getStatus().equals(AuditStatus.NOT_AUDIT)) {
            log.error("该商家审核状态为终态，无法进行审核，商家ID：{}",sellerId);
            throw new RuntimeException("该商家审核状态为终态，无法进行审核");
        }
        return sellerMapper.updateStatusBySellerId(sellerId, status);
    }

    @Override
    public Map<String, Object> selectAllByPage(SearchAllSellerRo searchRo, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        if (searchRo != null) {
            if (StringUtils.isNotBlank(searchRo.getSellerId())) {
                queryWrapper.lambda().eq(Seller::getSellerId, searchRo.getSellerId());
            }

            if (StringUtils.isNotBlank(searchRo.getName())) {
                queryWrapper.lambda().eq(Seller::getName, searchRo.getName());
            }

            if (StringUtils.isNotBlank(searchRo.getNickName())) {
                queryWrapper.lambda().eq(Seller::getNickName, searchRo.getNickName());
            }
            if (searchRo.getStatus() !=null) {
                queryWrapper.lambda().eq(Seller::getStatus, searchRo.getStatus());
            }
            queryWrapper.lambda().orderByDesc(Seller::getCreateDate);
            IPage<Seller> page = new Page<>(pageNum, pageSize);
            IPage<Seller> selectPage = sellerMapper.selectPage(page, queryWrapper);
            map.put("sellerVoList", selectPage.getRecords().stream().map(seller -> mapper.map(seller, SellerVo.class)).collect(Collectors.toList()));
            map.put("total", selectPage.getTotal());
        }
        return map;
    }
}
