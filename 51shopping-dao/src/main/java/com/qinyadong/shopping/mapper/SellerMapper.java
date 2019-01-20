package com.qinyadong.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinyadong.shopping.emnus.AuditStatus;
import com.qinyadong.shopping.entity.Seller;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 */
public interface SellerMapper extends BaseMapper<Seller> {

    /**
     * 获取
     * @param sellerId
     * @return
     */
    @Select("select password from tb_seller where seller_id = #{sellerId}")
    String getPasswordBySellerId(@Param("sellerId") String sellerId);

    /**
     * 根据ID更新密码
     * @param password
     * @param sellerId
     * @return
     */
    @Update("update tb_seller set password = #{password} where seller_id = #{sellerId}")
    int updatePassword(@Param("password") String password,@Param("sellerId") String sellerId);

    /**
     * 更新审核状态
     * @param sellerId
     * @param status
     * @return
     */
    @Update("update tb_seller set status = #{status} where seller_id = #{sellerId}")
    int updateStatusBySellerId(@Param("sellerId") String sellerId, @Param("status") AuditStatus status);
}
