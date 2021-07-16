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
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public Msg findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return Msg.sucess().data("items",list);
    }
    //逻辑删除讲师
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/{id}")
    public Msg removeTeacher(@ApiParam(name = "id", value = "讲师ID") @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        return flag ? Msg.sucess():Msg.error();
    }

    //分页功能
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public Msg pageTeacher(@ApiParam(name = "current", value = "当前页") @PathVariable long current,
                           @ApiParam(name = "limit", value = "每页记录数") @PathVariable long limit){
        //创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        //调用方法实现分页,底层封装,把分页数据封装到teacherPage中
        teacherService.page(teacherPage,null);
        //总记录数
        long total = teacherPage.getTotal();
        //返回每页数据的List集合
        List<EduTeacher> records = teacherPage.getRecords();
        return Msg.sucess().data("total",total).data("rows",records);
    }
    @ApiOperation(value = "分页查询讲师(条件)")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Msg pageTeacherCondition(@ApiParam(name = "current", value = "当前页") @PathVariable long current,
                                    @ApiParam(name = "limit", value = "每页记录数") @PathVariable long limit,
                                    @RequestBody TeacherQuery teacherQuery){//TeacherQuery可以写成@RequestBody(required = false) TeacherQuery必须使用post方法提交,也可以直接写成一个参数
        //创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //判断条件值是否为空,如果不为空就拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("begin", begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("begin",end);
        }
        wrapper.orderByDesc("gmt_modified");
        //调用方法实现分页,底层封装,把分页数据封装到teacherPage中
        teacherService.page(teacherPage,wrapper);
        //总记录数
        long total = teacherPage.getTotal();
        //返回每页数据的List集合
        List<EduTeacher> records = teacherPage.getRecords();
        return Msg.sucess().data("total",total).data("rows",records);
    }
    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public Msg addtTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        return save?Msg.sucess():Msg.error();
    }

    @ApiOperation(value = "查询讲师")
    @GetMapping("/getTeacher/{id}")
    public Msg getTeacher(@ApiParam(name = "id", value = "讲师id") @PathVariable long id){
        EduTeacher teacher = teacherService.getById(id);
        return Msg.sucess().data("teacher",teacher);
    }

    @ApiOperation(value = "修改讲师")
    @PostMapping("/updateTeacher")
    public Msg updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean result = teacherService.updateById(eduTeacher);
        return result?Msg.sucess():Msg.error();
    }

}

