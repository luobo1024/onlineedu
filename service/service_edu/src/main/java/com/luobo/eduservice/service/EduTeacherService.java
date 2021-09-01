package com.luobo.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobo.eduservice.entity.vo.TeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author luobo
 * @since 2021-06-20
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> teacherPage);
    //获取所有讲师
    List<EduTeacher> findAllTeacher();
    //逻辑删除讲师
    boolean removeTeacherById(String id);
    //分页查询讲师
    Map<String,Object> getTeacherByPage(long current, long limit);
    //分页带条件查询讲师
    Map<String, Object> getTeacherByPageWithCondition(long current, long limit, TeacherQuery teacherQuery);
}
