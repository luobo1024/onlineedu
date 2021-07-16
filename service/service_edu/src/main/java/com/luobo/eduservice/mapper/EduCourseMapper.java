package com.luobo.eduservice.mapper;

import com.luobo.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luobo.eduservice.entity.frontvo.CourseWebVo;
import com.luobo.eduservice.entity.vo.CoursePublishVo;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getCoursePublishVo(@Param("id") String id);

    CourseWebVo getBaseCourseInfo(String courseId);
}
