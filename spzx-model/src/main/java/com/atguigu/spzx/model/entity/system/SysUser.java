package com.atguigu.spzx.model.entity.system;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class SysUser extends BaseEntity {
    private String userName;
    private String password;
    private String name;
    private String phone;
    private String avatar;
    private String description;
    private Integer status;

}
