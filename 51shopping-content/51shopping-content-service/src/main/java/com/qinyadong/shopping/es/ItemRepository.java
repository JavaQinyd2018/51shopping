package com.qinyadong.shopping.es;

import com.qinyadong.shopping.po.ItemPo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: Yadong Qin
 * @Date: 2019/2/23
 */
public interface ItemRepository extends ElasticsearchRepository<ItemPo, Long> {


}
