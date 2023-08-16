package com.atguigu.spzx.model.dto.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class SysUserDto {

    private String keyword ;

    private String createTimeBegin ;

    private String createTimeEnd;

}