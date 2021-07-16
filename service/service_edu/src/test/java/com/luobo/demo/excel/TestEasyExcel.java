package com.luobo.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;


public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作
        //1.设置写入文件夹地址和excel文件名称
        String fileName = "D:\\Write.xlsx";
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("tom" + i);
            list.add(data);
        }
       // EasyExcel.write(fileName, DemoData.class).sheet("学生").doWrite(list);
        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();

    }

}
