package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.ro.AuditSellerRo;
import com.qinyadong.shopping.ro.SearchAllSellerRo;
import com.qinyadong.shopping.ro.SearchSellerRo;
import com.qinyadong.shopping.vo.SellerVo;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
public interface SellerIntegration {

    /**
     * 分页
     * @param searchSellerRoPageRo
     * @return
     */
    PageResult<SellerVo> searchByPage(PageRo<SearchSellerRo> searchSellerRoPageRo);

    /**
     * 获取
     * @param sellerId
     * @return
     */
    Result<SellerVo> getBySellerId(String sellerId);

    /**
     * 审核
     * @param auditSellerRo
     * @return
     */
    Result<Void> auditSellerStatus(AuditSellerRo auditSellerRo);

    /**
     * 获取所有的
     * @param sellerRoPageRo
     * @return
     */
    PageResult<SellerVo> searchAllByPage(PageRo<SearchAllSellerRo> sellerRoPageRo);
}
