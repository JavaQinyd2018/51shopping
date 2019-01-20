package com.qinyadong.shopping.ro;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.qinyadong.shopping.base.BaseRo;
import com.qinyadong.shopping.emnus.AuditStatus;
import com.qinyadong.shopping.emnus.MarketableType;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/6
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AddGoodsRo extends BaseRo {
    private String sellerId;
    @NotNull(message = "商品名称不能为空")
    public String goodsName;
    public Long defaultItemId;
    private AuditStatus auditStatus;
    private MarketableType marketableType;
    private Long brandId;
    private String caption;
    private Long category1Id;
    private Long category2Id;
    private Long category3Id;
    private String smallPic;
    @JSONField(format="#0")
    @NotNull(message = "商品价格不能为空")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal price;
    private Long typeTemplateId;
    private String isEnableSpec;
    private String isDelete;
}
