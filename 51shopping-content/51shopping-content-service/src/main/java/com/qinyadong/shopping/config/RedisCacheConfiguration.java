package com.qinyadong.shopping.config;

import com.qinyadong.shopping.cache.FastJson2JsonRedisSerializer;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * @Author: Yadong Qin
 * @Date: 2018/11/9
 */
//@EnableCaching开启缓存注解
@EnableCaching
@Configuration
public class RedisCacheConfiguration {

    @Bean
    public RedisSerializer<T> fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<T>(T.class);
    }

    /**
     * 这个模板bean已经在RedisAutoConfiguration配置过了，这边通过fastJson去作为序列化器
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(fastJson2JsonRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setDefaultSerializer(fastJson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
