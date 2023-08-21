package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.ProductSpecMapper;
import com.atguigu.spzx.manager.service.ProductSpecService;
import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecServiceImpl implements ProductSpecService {
    @Autowired
    ProductSpecMapper productSpecMapper;
    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<ProductSpec> byPage = productSpecMapper.findByPage();
        return PageInfo.of(byPage);
    }

    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec);
    }

    @Override
    public void delete(String id) {
        productSpecMapper.delete(id);
    }

    @Override
    public ProductSpec getById(String id) {
        return productSpecMapper.getById(id);

    }

    @Override
    public void update(ProductSpec productSpec) {
        productSpecMapper.update(productSpec);
    }

    @Override
    public List<ProductSpec> findAll() {
        return productSpecMapper.findAll();

    }
}
