package com.qinyadong.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinyadong.shopping.entity.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    @Insert(
            "INSERT INTO `tb_goods` (\n" +
                    "\tseller_id,\n" +
                    "\tgoods_name,\n" +
                    "\tdefault_item_id,\n" +
                    "\taudit_status,\n" +
                    "\tmarketable_type,\n" +
                    "\tbrand_id,\n" +
                    "\tcaption,\n" +
                    "\tcategory1_id,\n" +
                    "\tcategory2_id,\n" +
                    "\tcategory3_id,\n" +
                    "\tsmall_pic,\n" +
                    "\tprice,\n" +
                    "\ttype_template_id,\n" +
                    "\tis_enable_spec,\n" +
                    "\tis_delete,\n" +
                    "\tcreate_date,\n" +
                    "\tupdate_date\n" +
                    ")\n" +
                    "VALUES\n" +
                    "\t(\n" +
                    "\t#{goods.sellerId},\n" +
                    "\t#{goods.goodsName},\n" +
                    "\t#{goods.defaultItemId},\n" +
                    "\t#{goods.auditStatus},\n" +
                    "\t#{goods.marketableType},\n" +
                    "\t#{goods.brandId},\n" +
                    "\t#{goods.caption},\n" +
                    "\t#{goods.category1Id},\n" +
                    "\t#{goods.category2Id},\n" +
                    "\t#{goods.category3Id},\n" +
                    "\t#{goods.smallPic},\n" +
                    "\t#{goods.price},\n" +
                    "\t#{goods.typeTemplateId},\n" +
                    "\t#{goods.isEnableSpec},\n" +
                    "\t#{goods.isDelete},\n" +
                    "\t#{goods.createDate},\n" +
                    "\t#{goods.updateDate}\n" +
                    ")"
    )
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    /**
     * 插入返回主键
     */
    int save(@Param("goods") Goods goods);
}
