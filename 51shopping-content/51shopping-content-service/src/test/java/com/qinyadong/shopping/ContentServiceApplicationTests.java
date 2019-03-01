package com.qinyadong.shopping;

import com.qinyadong.shopping.es.ItemRepository;
import com.qinyadong.shopping.po.ItemPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentServiceApplicationTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void contextLoads() {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(100L);
        userInfo.setUsername("zhangsan");
        userInfo.setAddress("hangzhou");
        IndexQuery indexQuery = new IndexQueryBuilder().withIndexName("testuserinfo").withId(userInfo.getId().toString()).withObject(userInfo).build();
        elasticsearchTemplate.index(indexQuery);
    }

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void test1() {
        ItemPo itemPo = new ItemPo();
        itemPo.setBrand("测试");
        itemPo.setPrice(new BigDecimal("90.0"));
        ItemPo save = itemRepository.save(itemPo);
        System.out.println(save);
    }
}

