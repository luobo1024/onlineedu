package com.luobo.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobo.eduservice.entity.frontvo.CourseFrontVo;
import com.luobo.eduservice.entity.frontvo.CourseWebVo;
import com.luobo.eduservice.entity.vo.CourseInfoVo;
import com.luobo.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfoById(String courseId);

    void updateCourseInfoById(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishInfo(String courseId);

    void removeCourseById(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);

}
