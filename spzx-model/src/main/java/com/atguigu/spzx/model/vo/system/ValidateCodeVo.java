package com.atguigu.spzx.model.vo.system;

import lombok.Data;

@Data
public class ValidateCodeVo {

    private String codeKey ;        // 验证码的key
    private String codeValue ;      // 图片验证码对应的字符串数据

}