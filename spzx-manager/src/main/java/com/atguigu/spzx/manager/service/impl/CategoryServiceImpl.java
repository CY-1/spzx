package com.atguigu.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.manager.excelListener.ExcelListener;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper ;

    @Override
    public List<Category> findByParentId(Long parentId) {

        // 根据分类id查询它下面的所有的子分类数据
        List<Category> categoryList = categoryMapper.selectByParentId(parentId);
        if(!CollectionUtils.isEmpty(categoryList)) {

            // 遍历分类的集合，获取每一个分类数据
            categoryList.forEach(item -> {

                // 查询该分类下子分类的数量
                int count = categoryMapper.countByParentId(item.getId());
                if(count > 0) {
                    item.setHasChildren(true);
                } else {
                    item.setHasChildren(false);
                }

            });
        }
        return categoryList;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        // 设置响应结果类型
        try{
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("分类数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            List<Category> categoryList = categoryMapper.selectAll();
            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>(categoryList.size());
            for(Category category : categoryList) {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category, categoryExcelVo, CategoryExcelVo.class);
                categoryExcelVoList.add(categoryExcelVo);
            }
            // 写出数据到浏览器端
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类数据").doWrite(categoryExcelVoList);
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

    @Override
    public void importData(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>();  // 创建一个监听器对象
            EasyExcel.read(inputStream, CategoryExcelVo.class, excelListener).sheet().doRead();    // 解析excel表格
            List<CategoryExcelVo> categoryExcelVoList = excelListener.getDatas();       // 获取解析到的数据
            // 如果解析到的数据不为空，那么此时将解析到的对象转换成Category对象
            List<Category> categoryList = new ArrayList<>();
            if(!CollectionUtils.isEmpty(categoryExcelVoList)) {
                for(CategoryExcelVo categoryExcelVo : categoryExcelVoList) {
                    Category category = new Category();
                    BeanUtils.copyProperties(categoryExcelVo, category, Category.class);
                    categoryList.add(category);
                }

                // 进行数据的批量保存
                categoryMapper.batchInsert(categoryList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}