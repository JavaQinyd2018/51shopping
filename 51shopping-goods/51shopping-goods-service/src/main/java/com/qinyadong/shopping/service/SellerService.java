package com.qinyadong.shopping.service;

import com.qinyadong.shopping.base.BaseService;
import com.qinyadong.shopping.common.RpcResponse;
import com.qinyadong.shopping.emnus.AuditStatus;
import com.qinyadong.shopping.entity.Seller;
import com.qinyadong.shopping.ro.SearchAllSellerRo;
import com.qinyadong.shopping.ro.SearchSellerRo;

import java.util.Map;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
public interface SellerService extends BaseService<Seller> {

    /**
     * 获取商家
     * @param sellerId
     * @return
     */
    Seller getBySellerId(String sellerId);

    /**
     * 获取密码
     * @param sellerId
     * @return
     */
    String getPasswordBySellerId(String sellerId);

    /**
     * 更新密码
     * @param newPassword
     * @return
     */
    int updatePassword(String newPassword, String sellerId);

    /**
     * 分页查询
     * @param searchRo
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String, Object> selectByPage(SearchSellerRo searchRo, Integer pageNum, Integer pageSize);

    /**
     * 审核
     * @param sellerId
     * @param status
     * @return
     */
    int auditSellerStatus(String sellerId, AuditStatus status);

    /**
     * 获取所有
     * @param searchRo
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String, Object> selectAllByPage(SearchAllSellerRo searchRo, Integer pageNum, Integer pageSize);
}
