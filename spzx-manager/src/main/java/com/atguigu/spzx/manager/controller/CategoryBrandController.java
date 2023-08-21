package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.CategoryBrandService;
import com.atguigu.spzx.model.dto.system.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.Brand;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product/categoryBrand")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService ;

    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<CategoryBrand>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, CategoryBrandDto CategoryBrandDto) {
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(page, limit, CategoryBrandDto);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }
    @PostMapping("")
    public Result addBrandCategory(@RequestBody  CategoryBrandDto categoryBrandDto){
        categoryBrandService.addBrandCategory(categoryBrandDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @PutMapping("/updateById")
    public Result UpdateBrandCategory(@RequestBody CategoryBrand categoryBrand){
        categoryBrandService.updateBrandCategory(categoryBrand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @DeleteMapping("/deleteById/{id}")
    public Result DeleteBrandCategory(@PathVariable Long id){
        categoryBrandService.deleteBrandCategory(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @GetMapping("/findBrandByCategoryId/{categoryId}")
    public Result findBrandByCategoryId(@PathVariable Long categoryId) {
        List<Brand> brandList =   categoryBrandService.findBrandByCategoryId(categoryId);
        return Result.build(brandList , ResultCodeEnum.SUCCESS) ;
    }
}