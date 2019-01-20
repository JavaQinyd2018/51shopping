package com.qinyadong.shopping.cache;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/20
 */
public class CacheHelper {

    public static String assembleRedisKey(String moduleName, String serviceName) {
        return String.format("%s:%s:%s",moduleName.toUpperCase(),serviceName, "base".toUpperCase());
    }
}
