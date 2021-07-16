package com.luobo.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobo.eduservice.entity.EduSubject;
import com.luobo.eduservice.entity.excel.SubjectData;
import com.luobo.eduservice.entity.excel.SubjectTree;
import com.luobo.eduservice.listener.SubjectExcelListener;
import com.luobo.eduservice.mapper.EduSubjectMapper;
import com.luobo.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    EduSubjectService eduSubjectService;
    @Override
    public void saveSubject(MultipartFile file) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            EasyExcel.read(is, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<SubjectTree> getAllSubject() {
        //一级
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        List<EduSubject> oneSubjcet = eduSubjectService.list(wrapper);
        List<SubjectTree> subjectTrees = new ArrayList<>();
      //  Integer id = 1;
        for(EduSubject one: oneSubjcet){
            SubjectTree subject = new SubjectTree();
            subject.setLabel(one.getTitle());
            subject.setId(one.getId());
            subjectTrees.add(subject);
         //   id++;
        }
        //二级
        wrapper = new QueryWrapper<>();
        wrapper.ne("parent_id", 0);
        List<EduSubject> twoSubjcet = eduSubjectService.list(wrapper);
        for(SubjectTree subjectTree: subjectTrees){
            wrapper = new QueryWrapper<>();
            wrapper.eq("title",subjectTree.getLabel());
            EduSubject one = eduSubjectService.getOne(wrapper);
            one.getId();
            for(EduSubject eduSubject: twoSubjcet){
                if(eduSubject.getParentId().equals(one.getId())){
                    SubjectTree subject = new SubjectTree();
                    subject.setLabel(eduSubject.getTitle());
                    subject.setId(eduSubject.getId());
                    subjectTree.getChildren().add(subject);
                  //  id++;
                }
            }
        }



        return subjectTrees;
    }
}
