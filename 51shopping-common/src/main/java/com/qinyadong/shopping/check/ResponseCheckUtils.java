package com.qinyadong.shopping.check;

import com.qinyadong.shopping.common.RpcCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/4
 * Rpc响应结果校验
 */
public class ResponseCheckUtils {

        public ResponseCheckUtils() {
        }

        private static String getSuccessCode() {
            return "00000000";
        }

        public static boolean isSuccess(RpcCode rpcCode) {
            return StringUtils.equals(getSuccessCode(), rpcCode.getCode());
        }

        public static boolean equals(String code, RpcCode rpcCode) {
            return code != null && (StringUtils.equals(code, rpcCode.getCode()));
        }
}
