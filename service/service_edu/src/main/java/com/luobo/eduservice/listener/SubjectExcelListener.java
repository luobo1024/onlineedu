package com.luobo.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobo.eduservice.entity.EduSubject;
import com.luobo.eduservice.entity.excel.SubjectData;
import com.luobo.eduservice.service.EduSubjectService;
import com.luobo.servicebase.exceptionhandler.GlobalExceptionHandler;
import lombok.SneakyThrows;

import java.util.Map;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //SubjectExcelListener不能注入
    public EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public SubjectExcelListener() {
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            System.out.println("error1");
        }

        if(existOneSubejct(subjectData.getOneSubjectName())){//判断是否存在一级分类
            EduSubject eduOneSubject = new EduSubject();
            eduOneSubject.setTitle(subjectData.getOneSubjectName());
            eduOneSubject.setParentId("0");
            eduSubjectService.save(eduOneSubject);
        }
        String api;
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",subjectData.getOneSubjectName());
        wrapper.eq("parent_id","0");
        EduSubject eduOneSubject = eduSubjectService.getOne(wrapper);
        if(eduOneSubject != null) {
            if (existTwoSubejct(subjectData.getTwoSubjectName(), eduOneSubject.getId())) {
                EduSubject eduTwoSubject = new EduSubject();
                eduTwoSubject.setTitle(subjectData.getTwoSubjectName());
                eduTwoSubject.setParentId(eduOneSubject.getId());
                eduSubjectService.save(eduTwoSubject);
            }
        }

    }
    //判断一级分类不能重复添加
    private boolean existOneSubejct(String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        int count = eduSubjectService.count(wrapper);
        if(count != 0){
            return false;
        }else {
            return true;
        }
    }
    private boolean existTwoSubejct(String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        int count = eduSubjectService.count(wrapper);
        if(count != 0){
            return false;
        }else {
            return true;
        }
    }
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
