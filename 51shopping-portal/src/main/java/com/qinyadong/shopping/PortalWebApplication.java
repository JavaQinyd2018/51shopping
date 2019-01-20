package com.qinyadong.shopping;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class PortalWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalWebApplication.class, args);
    }

}

