package com.atguigu.spzx.model.vo.common;

import lombok.Getter;

/**
 * 将状态码和它的描述信息 封装成枚举对象  枚举对象的名称定义为见名知意的变量
 * 将来创建Result对象时直接通过已经定义好的枚举对象初始化
 */
@Getter
public enum ResultCodeEnum {
    //创建枚举类实例
    SUCCESS(200 , "成功"),
    LOGIN_ERROR(201 , "账号或密码错误"),
    UNKNOW_ERROR(-1 , "服务器内部错误");

    private Integer code;
    private String message;
    private ResultCodeEnum(Integer code,
                           String message){
        this.code = code;
        this.message = message;
    }
}
