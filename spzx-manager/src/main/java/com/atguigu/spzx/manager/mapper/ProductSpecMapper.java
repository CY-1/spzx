package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSpecMapper {
    public abstract List<ProductSpec> findByPage();

    void save(ProductSpec productSpec);

    void delete(String id);

    ProductSpec getById(String id);

    void update(ProductSpec productSpec);

    List<ProductSpec> findAll();
}