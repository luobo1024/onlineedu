package com.luobo.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobo.commonutils.Msg;
import com.luobo.eduservice.entity.EduCourse;
import com.luobo.eduservice.entity.EduTeacher;
import com.luobo.eduservice.service.EduCourseService;
import com.luobo.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduTeacherService eduTeacherService;
    //查询前八条热门课程,前四条名称
    @GetMapping("/index")
    public Msg index(){
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<EduCourse>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> courseList = eduCourseService.list(wrapper);

        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 8");
        List<EduTeacher> teacherList = eduTeacherService.list(wrapperTeacher);
        return Msg.sucess().data("courseList",courseList).data("teacherList",teacherList);
    }
}
