package com.atguigu.spzx.model.dto.system;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AssginMenuDto {
    private Long roleId;							// 角色id
    private List<Map<String , Number>> menuIdList;	// 选中的菜单id的集合 , Map中包含了2部分的数据：菜单id，isHalf
}