package com.qinyadong.shopping.integration;

import com.qinyadong.shopping.common.PageResult;
import com.qinyadong.shopping.common.PageRo;
import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.ro.AddSellerRo;
import com.qinyadong.shopping.ro.SearchSellerRo;
import com.qinyadong.shopping.ro.UpdatePasswordRo;
import com.qinyadong.shopping.ro.UpdateSellerRo;
import com.qinyadong.shopping.vo.SellerVo;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
public interface SellerIntegration {

    /**
     * 添加商家
     * @param addSellerRo
     * @return
     */
    Result<Void> addSeller(AddSellerRo addSellerRo);

    /**
     * 获取
     * @param sellerId
     * @return
     */
    Result<SellerVo> getBySellerId(String sellerId);

    /**
     * 更新
     * @param sellerVo
     * @return
     */
    Result<Void> updateSeller(UpdateSellerRo sellerVo);

    /**
     * 根据商家ID获取密码
     * @param sellerId
     * @return
     */
    Result<String> getPasswordBySellerId(String sellerId);
    /**
     * 修改密码
     * @param updatePasswordRo
     * @return
     */
    Result<Void> updatePassword(UpdatePasswordRo updatePasswordRo);

    /**
     * 分页查询
     * @param searchSellerRoPageRo
     * @return
     */

}
