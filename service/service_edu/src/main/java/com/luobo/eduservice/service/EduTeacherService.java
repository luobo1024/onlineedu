package com.luobo.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
