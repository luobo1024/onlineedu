package com.luobo.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.commonutils.Msg;
import com.luobo.eduservice.entity.EduTeacher;
import com.luobo.eduservice.entity.vo.TeacherQuery;
import com.luobo.eduservice.service.EduTeacherService;
import com.luobo.eduservice.service.impl.EduTeacherServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author luobo
 * @since 2021-06-20
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    EduTeacherService teacherService = new EduTeacherServiceImpl();

    //查询所有讲师
    @ApiOperation(value = "获取所有讲师")
    @GetMapping("/findAll")
    public Msg findAllTeacher(){
        List<EduTeacher> list =  teacherService.findAllTeacher();
        return Msg.success().data("items",list);
    }
    //逻辑删除讲师
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/{id}")
    public Msg removeTeacher(@ApiParam(name = "id", value = "讲师ID") @PathVariable String id){
        boolean flag = teacherService.removeTeacherById(id);
        return flag ? Msg.success():Msg.error();
    }

    //分页功能
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public Msg pageTeacher(@ApiParam(name = "current", value = "当前页") @PathVariable long current,
                           @ApiParam(name = "limit", value = "每页记录数") @PathVariable long limit){

        Map<String,Object> resultMap = teacherService.getTeacherByPage(current,limit);
        return Msg.success().data("total",resultMap.get("total")).data("rows",resultMap.get("rows"));
    }
    @ApiOperation(value = "分页查询讲师(条件)")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Msg pageTeacherCondition(@ApiParam(name = "current", value = "当前页") @PathVariable long current,
                                    @ApiParam(name = "limit", value = "每页记录数") @PathVariable long limit,
                                    @RequestBody TeacherQuery teacherQuery){//TeacherQuery可以写成@RequestBody(required = false) TeacherQuery必须使用post方法提交,也可以直接写成一个参数
        Map<String,Object> resultMap = teacherService.getTeacherByPageWithCondition(current,limit,teacherQuery);
        return Msg.success().data("total",resultMap.get("total")).data("rows",resultMap.get("rows"));
    }
    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public Msg addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        return save?Msg.success():Msg.error();
    }

    @ApiOperation(value = "查询讲师")
    @GetMapping("/getTeacher/{id}")
    public Msg getTeacher(@ApiParam(name = "id", value = "讲师id") @PathVariable long id){
        EduTeacher teacher = teacherService.getById(id);
        return Msg.success().data("teacher",teacher);
    }

    @ApiOperation(value = "修改讲师")
    @PostMapping("/updateTeacher")
    public Msg updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean result = teacherService.updateById(eduTeacher);
        return result?Msg.success():Msg.error();
    }

}

