package com.qinyadong.shopping.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/21
 * ElasticSearch配置
 */
@Configuration
public class ElasticSearchConfiguration {

    /**
     * * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
     * * 解决netty冲突后初始化client时还会抛出异常
     * * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
     * ---------------------
     */
    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}
