package com.qinyadong.shopping.po;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Yadong Qin
 * @Date: 2019/1/28
 */
@Data
public class BasePo implements Serializable {
    @Id
    private Long id;
    private Date createDate;
    private Date updateDate;
}
