package com.luobo.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.commonutils.Msg;
import com.luobo.eduservice.entity.EduCourse;
import com.luobo.eduservice.entity.EduTeacher;
import com.luobo.eduservice.entity.vo.CourseInfoVo;
import com.luobo.eduservice.entity.vo.CoursePublishVo;
import com.luobo.eduservice.entity.vo.TeacherQuery;
import com.luobo.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    //添加课程基本信息
    @PostMapping("/addCourseInfo")
    public Msg addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return Msg.success().data("courseId",id);
    }
    @GetMapping("/getCourseInfo/{courseId}")
    public Msg getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoById = eduCourseService.getCourseInfoById(courseId);
        return Msg.success().data("course",courseInfoById);
    }
    @PostMapping("/updateCourseInfo")
    public Msg updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfoById(courseInfoVo);
        return Msg.success();
    }
    @GetMapping("/getCoursePublishInfo/{courseId}")
    public Msg getCoursePublishInfo(@PathVariable String courseId){
        CoursePublishVo courseInfo =  eduCourseService.getCoursePublishInfo(courseId);
        return Msg.success().data("courseInfo",courseInfo);
    }
    //课程最终发布
    @PostMapping("publishCourse/{courseId}")
    public Msg publishCourse(@PathVariable String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Msg.success();
    }
    @GetMapping
    public Msg getCourseList(){
        List<EduCourse> list = eduCourseService.list(null);
        return Msg.success().data("list",list);
    }
    //分页功能
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public Msg pageTeacherCondition(@ApiParam(name = "current", value = "当前页") @PathVariable long current,
                                    @ApiParam(name = "limit", value = "每页记录数") @PathVariable long limit,
                                    @RequestBody EduCourse eduCourse){//TeacherQuery可以写成@RequestBody(required = false) TeacherQuery必须使用post方法提交,也可以直接写成一个参数
        //创建page对象
        Page<EduCourse> coursePage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断条件值是否为空,如果不为空就拼接条件
        String title = eduCourse.getTitle();
        String status = eduCourse.getStatus();
        Date begin = eduCourse.getGmtCreate();
        Date end = eduCourse.getGmtModified();
        if(!StringUtils.isEmpty(title)){
            wrapper.like("title", title);
        }
        if(!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("begin", begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("begin",end);
        }
        wrapper.orderByDesc("gmt_modified");
        //调用方法实现分页,底层封装,把分页数据封装到teacherPage中
        eduCourseService.page(coursePage,wrapper);
        //总记录数
        long total = coursePage.getTotal();
        //返回每页数据的List集合
        List<EduCourse> records = coursePage.getRecords();
        return Msg.success().data("total",total).data("rows",records);
    }
    @DeleteMapping("{courseId}")
    public Msg deleteCourse(@PathVariable String courseId){
        eduCourseService.removeCourseById(courseId);
        return Msg.success();
    }
}

