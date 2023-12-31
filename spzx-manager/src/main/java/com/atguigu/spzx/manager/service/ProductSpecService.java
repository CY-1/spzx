package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductSpecService {
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    void save(ProductSpec productSpec);

    void delete(String id);

    ProductSpec getById(String id);

    void update(ProductSpec productSpec);

    List<ProductSpec> findAll();
}
