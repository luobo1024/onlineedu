package com.luobo.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.eduservice.entity.EduTeacher;
import com.luobo.eduservice.mapper.EduTeacherMapper;
import com.luobo.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author luobo
 * @since 2021-06-20
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> teacherPage) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        baseMapper.selectPage(teacherPage, wrapper);
        List<EduTeacher> records = teacherPage.getRecords();
        long total = teacherPage.getTotal();
        long pages = teacherPage.getPages();
        long current = teacherPage.getCurrent();
        long size = teacherPage.getSize();
        boolean hasNext = teacherPage.hasNext();
        boolean hasPrevious = teacherPage.hasPrevious();
        Map<String, Object> map = new HashMap<>();
        map.put("items",records);
        map.put("pages",pages);
        map.put("current",current);
        map.put("hasNext", hasNext);
        map.put("hasPrevious",hasPrevious);
        map.put("size",size);
        return map;
    }
}
