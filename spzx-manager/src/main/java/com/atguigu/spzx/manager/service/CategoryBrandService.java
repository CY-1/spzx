package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.atguigu.spzx.model.vo.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryBrandService {
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    void addBrandCategory(CategoryBrandDto categoryBrandDto);

    void updateBrandCategory(CategoryBrand categoryBrand);

    void deleteBrandCategory(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
