package com.atguigu.spzx.model.vo.product;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class Brand extends BaseEntity {

	private String name;
	private String logo;

}