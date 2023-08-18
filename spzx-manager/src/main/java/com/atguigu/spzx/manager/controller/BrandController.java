package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.Brand;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/admin/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService ;

    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<Brand>> findByPage(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<Brand> pageInfo = brandService.findByPage(page, limit);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }
    @PostMapping("")
    public Result addNewBrand(@RequestBody Brand brand){
        brandService.addNewBrand(brand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    @GetMapping("/{id}" )
    public Result getBrandById(@PathVariable Long id){
        Brand brand = brandService.getBrandById(id);
        return Result.build(brand,ResultCodeEnum.SUCCESS);
    }
    @PutMapping("")
    public Result updateBrandById(@RequestBody Brand brand){
        brandService.updateBrandById(brand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    @DeleteMapping("/{id}" )
    public Result deleteBrandById(@PathVariable Long id){
        brandService.deleteBrandById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    @GetMapping("/find/findAll")
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }
}