package com.atguigu.spzx.model.dto.system;

import lombok.Data;

import java.util.List;

@Data
public class AddUserRoleDto {
    private Long userId;
    private List<Integer> roleIdList;
}
