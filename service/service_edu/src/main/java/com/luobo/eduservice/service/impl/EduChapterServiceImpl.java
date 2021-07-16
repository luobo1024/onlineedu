package com.luobo.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luobo.eduservice.entity.ChapterVo;
import com.luobo.eduservice.entity.EduChapter;
import com.luobo.eduservice.entity.EduSubject;
import com.luobo.eduservice.entity.EduVideo;
import com.luobo.eduservice.mapper.EduChapterMapper;
import com.luobo.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luobo.eduservice.service.EduVideoService;
import com.luobo.servicebase.exceptionhandler.EduException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    EduVideoService eduVideoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        List<EduChapter> oneList = this.list(wrapper1);
        List<ChapterVo> chapterVos = new ArrayList<>();
        //一级
        for(EduChapter eduChapter: oneList){
            ChapterVo chapterVo = new ChapterVo();
            chapterVo.setId(eduChapter.getId());
            chapterVo.setTitle(eduChapter.getTitle());
            chapterVos.add(chapterVo);
        }
        //二级
        QueryWrapper<EduVideo> wrapper2= new QueryWrapper<>();
        wrapper2.eq("course_id",courseId);
        List<EduVideo> twolist = eduVideoService.list(wrapper2);
        for(ChapterVo chapterVo:chapterVos){
            List<ChapterVo> childrens = new ArrayList<>();
            for(EduVideo eduVideo: twolist){
                if(chapterVo.getId().equals(eduVideo.getChapterId())){
                    ChapterVo children = new ChapterVo();
                    children.setId(eduVideo.getId());
                    children.setTitle(eduVideo.getTitle());
                    children.setVideoSourceId(eduVideo.getVideoSourceId());
                    childrens.add(children);
                }
            }
            chapterVo.setChildren(childrens);

        }

        return chapterVos;
    }

    @Override
    public boolean deleteChapterById(String chapterId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(wrapper);
        if(count > 0){
            throw new EduException(20001,"不允许删除");
        }else {
            int i = baseMapper.deleteById(chapterId);
            if(i > 0){
                return true;
            }
        }
        return false;
    }
}
