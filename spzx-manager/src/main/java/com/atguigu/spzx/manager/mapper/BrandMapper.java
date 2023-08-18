package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.vo.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    List<Brand> findByPage();

    void addNewBrand(Brand brand);

    Brand getBrandById(Long id);

    void updateBrandById(Brand brand);

    void deleteBrandById(Long id);

    List<Brand> findAll();
}
