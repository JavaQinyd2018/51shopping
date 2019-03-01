package com.qinyadong.shopping.po;

import com.qinyadong.shopping.emnus.ItemStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/28
 * 搜索 persistent object
 */

@Getter
@Setter
@Document(indexName = "iteminfo",type = "item")
public class ItemPo extends BasePo{

    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;
    private String sellPoint;
    @Field(type = FieldType.Double)
    private BigDecimal price;
    private Integer stockCount;
    private Integer num;
    private String barcode;
    private String image;
    private Long categoryId;
    private ItemStatus status;
    private String itemSn;
    @Field(type = FieldType.Double)
    private BigDecimal costPrice;
    @Field(type = FieldType.Double)
    private BigDecimal marketPrice;
    private String isDefault;
    private Long goodsId;
    private String sellerId;
    private String cartThumbnail;
    private String category;
    private String brand;
    private String spec;
    private String seller;
}
