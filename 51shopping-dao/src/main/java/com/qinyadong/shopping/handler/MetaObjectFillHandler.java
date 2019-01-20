package com.qinyadong.shopping.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/1
 */
@Component("metaObjectFillHandler")
public class MetaObjectFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createDate = this.getFieldValByName("createDate", metaObject);
        if (createDate == null) {
            this.setFieldValByName("createDate",new Date(),metaObject);
        }
        Object updateDate = this.getFieldValByName("updateDate", metaObject);
        if (updateDate == null) {
            this.setFieldValByName("updateDate",new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateDate = this.getFieldValByName("updateDate", metaObject);
        if (updateDate == null) {
            this.setFieldValByName("updateDate",new Date(),metaObject);
        }
    }
}
