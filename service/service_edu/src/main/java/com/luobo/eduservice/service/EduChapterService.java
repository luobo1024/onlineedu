package com.luobo.eduservice.service;

import com.luobo.eduservice.entity.ChapterVo;
import com.luobo.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapterById(String chapterId);
}
