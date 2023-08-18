package com.atguigu.test;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.manager.excelListener.ExcelListener;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EasyExcelTest {

    @Test
    public void easyExcelReadTest() {
        String fileName = "D:\\java_learn_path\\spzx-parent\\spzx-manager\\src\\test\\resources\\分类数据.xlsx" ;
        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>();  // 创建一个监听器对象
        EasyExcel.read(fileName, CategoryExcelVo.class, excelListener).sheet().doRead();    // 解析excel表格
        List<CategoryExcelVo> excelVoList = excelListener.getDatas();       // 获取解析到的数据
        excelVoList.forEach(s -> System.out.println(s) );   // 进行遍历操作
    }

}