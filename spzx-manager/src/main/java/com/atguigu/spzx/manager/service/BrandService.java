package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.vo.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    void addNewBrand(Brand brand);

    Brand getBrandById(Long id);

    void updateBrandById(Brand brand);

    void deleteBrandById(Long id);

    List<Brand> findAll();
}
