package com.qinyadong.shopping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

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

}

