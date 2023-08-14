package com.atguigu.spzx.model.vo.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true) //链式调用
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    //不能通过无参构造器创建对象
    private Result(){

    }
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
    public Result<T> message(String message){
        this.setMessage(message);
        return this;
    }
    public Result<T> data(T data){
        this.setData(data);
        return this;
    }
    //构建Result对象的方法
    public static <T>Result<T> build(Integer code,String message,T data){
        Result<T> result = new Result<>();
        return result.code(code)
                .message(message)
                .data(data);
    }
    //使用枚举类对象创建Result的方法
    public static <T> Result<T> build( T data,ResultCodeEnum codeEnum ){
        return build(codeEnum.getCode() , codeEnum.getMessage() , data);
    }


}
