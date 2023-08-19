package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryBrandMapper {
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);

    void addBrandCategory(CategoryBrandDto categoryBrandDto);

    void updateBrandCategory(CategoryBrand categoryBrand);

    void deleteBrandCategory(Long id);
}
