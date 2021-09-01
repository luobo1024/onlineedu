package com.luobo.eduservice.controller;


import com.luobo.commonutils.Msg;
import com.luobo.eduservice.entity.EduSubject;
import com.luobo.eduservice.entity.excel.SubjectTree;
import com.luobo.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("/addSubject")
    public Msg addSubject(MultipartFile file){
        subjectService.saveSubject(file);
        return Msg.success();
    }
    @GetMapping("/getAllSubject")
    public Msg getAllSubject(){
        List<SubjectTree> allSubject = subjectService.getAllSubject();
        return Msg.success().data("list",allSubject);
    }
}

