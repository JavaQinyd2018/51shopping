package com.qinyadong.shopping.emnus;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/5
 * 审核状态
 */
public enum AuditStatus {

    /**
     * 未审核
     */
    NOT_AUDIT,
    /**
     * 审核通过
     */
    HAS_AUDITED,

    /**
     * 审核失败
     */
    AUDIT_FAIL,

    /**
     * 审核关闭
     */
    AUDIT_CLOSED;
}
