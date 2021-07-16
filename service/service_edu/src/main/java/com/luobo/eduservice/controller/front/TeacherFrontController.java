package com.luobo.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.commonutils.Msg;
import com.luobo.eduservice.entity.EduCourse;
import com.luobo.eduservice.entity.EduTeacher;
import com.luobo.eduservice.service.EduCourseService;
import com.luobo.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    //1.分页查询讲师
    @GetMapping("/getTeacherFrontList/{current}/{limit}")
    public Msg getTeacherFrontList(@PathVariable long current, @PathVariable long limit){
        Page<EduTeacher> teacherPage  = new Page<>(current,limit);
        Map<String, Object> map =  teacherService.getTeacherFrontList(teacherPage);
        return Msg.sucess().data(map);
    }
    @GetMapping("/getTeacherFrontInfo/{teacherId}")
    public Msg getTeacherFrontInfo(@PathVariable String teacherId){
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        EduTeacher eduTeacher = teacherService.getById(teacherId);
        List<EduCourse> list = courseService.list(wrapper);
        return Msg.sucess().data("teacher",eduTeacher).data("courseList", list);
    }
}
