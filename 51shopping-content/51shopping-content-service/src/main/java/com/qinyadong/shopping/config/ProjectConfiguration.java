package com.qinyadong.shopping.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/29
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = "goods.service",ignoreInvalidFields = false)
@PropertySource("classpath:config/app.properties")
@Component
public class ProjectConfiguration {

    private String url;
}
