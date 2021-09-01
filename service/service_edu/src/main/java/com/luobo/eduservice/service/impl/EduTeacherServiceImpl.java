package com.luobo.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.eduservice.entity.EduTeacher;
import com.luobo.eduservice.entity.vo.TeacherQuery;
import com.luobo.eduservice.mapper.EduTeacherMapper;
import com.luobo.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        map.put("total",total);
        return map;
    }

    @Override
    //获取所有讲师
    public List<EduTeacher> findAllTeacher() {
        return baseMapper.selectList(null);
    }

    @Override
    //逻辑删除讲师
    public boolean removeTeacherById(String id) {
        return baseMapper.deleteById(id) > 0 ? true : false;
    }

    @Override
    //分页查询讲师
    public Map<String,Object> getTeacherByPage(long current, long limit) {
        //创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        //调用方法实现分页,底层封装,把分页数据封装到teacherPage中
        baseMapper.selectPage(teacherPage,null);
        //总记录数
        long total = teacherPage.getTotal();
        //返回每页数据的List集合
        List<EduTeacher> records = teacherPage.getRecords();
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);
        return map;
    }

    @Override
    //分页带条件查询讲师
    public Map<String, Object> getTeacherByPageWithCondition(long current, long limit, TeacherQuery teacherQuery) {
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
        baseMapper.selectPage(teacherPage,wrapper);
        //总记录数
        long total = teacherPage.getTotal();
        //返回每页数据的List集合
        List<EduTeacher> records = teacherPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);
        return map;
    }

}
