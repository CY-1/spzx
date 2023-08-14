package com.atguigu.spzx.model.vo.system;

import lombok.Data;

@Data
public class LoginVo {

    private String token ;
    private String refresh_token ;		// 该字段不会存储对应的值

}