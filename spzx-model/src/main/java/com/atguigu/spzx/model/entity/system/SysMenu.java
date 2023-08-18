package com.atguigu.spzx.model.entity.system;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class SysMenu extends BaseEntity {

	private Long parentId;
	private String title;
	private String component;
	private Integer sortValue;
	private Integer status;

	// 下级列表
	private List<SysMenu> children;

}