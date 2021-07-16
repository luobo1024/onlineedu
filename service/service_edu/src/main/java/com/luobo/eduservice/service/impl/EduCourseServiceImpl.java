package com.luobo.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.commonutils.JwtUtils;
import com.luobo.eduservice.client.OrdersClient;
import com.luobo.eduservice.client.VodClient;
import com.luobo.eduservice.entity.*;
import com.luobo.eduservice.entity.frontvo.CourseFrontVo;
import com.luobo.eduservice.entity.frontvo.CourseWebVo;
import com.luobo.eduservice.entity.vo.CourseInfoVo;
import com.luobo.eduservice.entity.vo.CoursePublishVo;
import com.luobo.eduservice.mapper.EduCourseMapper;
import com.luobo.eduservice.service.EduChapterService;
import com.luobo.eduservice.service.EduCourseDescriptionService;
import com.luobo.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luobo.eduservice.service.EduVideoService;
import com.luobo.servicebase.exceptionhandler.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    EduChapterService eduChapterService;
    @Autowired
    VodClient vodClient;
    @Override
    @Transactional
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert <= 0){
            throw new EduException(20001,"添加课程信息失败");
        }
        //2.向课程简介表添加课程简介
        String cid = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        eduCourseDescription.setId(cid);
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);

        if (!save) {
            throw new EduException(20001,"添加课程信息失败");
        }
        return cid;

    }

    @Override
    public CourseInfoVo getCourseInfoById(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        EduCourseDescription  eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        BeanUtils.copyProperties(eduCourseDescription,courseInfoVo);
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfoById(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        baseMapper.updateById(eduCourse);
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo getCoursePublishInfo(String courseId) {
       return baseMapper.getCoursePublishVo(courseId);

    }

    @Override
    public void removeCourseById(String courseId) {
        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        //根据返回的所有视频id删除视频
        List<EduVideo> eduVideoList = eduVideoService.list(wrapper1);
        List<String> list = new ArrayList<>();
        for(EduVideo eduVideo : eduVideoList){
            if(!StringUtils.isEmpty(eduVideo.getVideoSourceId())){
                list.add(eduVideo.getVideoSourceId());
            }

        }
        if(list.size()>0){
            vodClient.deleteBatch(list);
        }

        eduVideoService.remove(wrapper1);

        QueryWrapper<EduChapter> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("course_id",courseId);
        eduChapterService.remove(wrapper2);

        eduCourseDescriptionService.removeById(courseId);

        int result = baseMapper.deleteById(courseId);
        if(result < 0){

        }
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())){
            wrapper.eq("subject_parent_id", courseFrontVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())){
            wrapper.eq("subject_id", courseFrontVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())){
            wrapper.orderByDesc("buy_count");
        }
        if(!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())){
            wrapper.orderByDesc("gmt_create");
        }
        if(!StringUtils.isEmpty(courseFrontVo.getPriceSort())){
            wrapper.orderByDesc("price");
        }
        baseMapper.selectPage(coursePage, wrapper);
        List<EduCourse> records = coursePage.getRecords();
        long total = coursePage.getTotal();
        long pages = coursePage.getPages();
        long current = coursePage.getCurrent();
        long size = coursePage.getSize();
        boolean hasNext = coursePage.hasNext();
        boolean hasPrevious = coursePage.hasPrevious();
        Map<String, Object> map = new HashMap<>();
        map.put("items",records);
        map.put("pages",pages);
        map.put("current",current);
        map.put("hasNext", hasNext);
        map.put("hasPrevious",hasPrevious);
        map.put("size",size);
        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }


}
