package com.atguigu.spzx.model.entity.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    private Long id;
    private Date createTime;
    private Date updateTime;
    //boolean: 基本类型两个值 true和false  自动生成的get set方法是 isXxx
    //Boolean: 包装类有三个值 true false  null
    private Integer isDeleted;

}
