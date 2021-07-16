package com.luobo.eduservice.controller;


import com.luobo.commonutils.Msg;
import com.luobo.eduservice.entity.ChapterVo;
import com.luobo.eduservice.entity.EduChapter;
import com.luobo.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    EduChapterService eduChapterService;

    @GetMapping("/getChapterVideo/{courseId}")
    public Msg getChapterVideo(@PathVariable String courseId){
       List<ChapterVo> videos = eduChapterService.getChapterVideoByCourseId(courseId);
        return Msg.sucess().data("Videos",videos);
    }
    @PostMapping("/addChapter")
    public Msg addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return Msg.sucess();

    }
    @GetMapping("/getChapterInfo/{chapterId}")
    public Msg getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return Msg.sucess().data("chapter", eduChapter);
    }
    @PostMapping("/updateChapter")
    public Msg updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return Msg.sucess();
    }
    @DeleteMapping("/deleteChapter/{chapterId}")
    public Msg deleteChapter(@PathVariable String chapterId){
        boolean result = eduChapterService.deleteChapterById(chapterId);
        return result?Msg.sucess():Msg.error();
    }


}

