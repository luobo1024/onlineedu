package com.luobo.eduservice.controller;


import com.luobo.commonutils.Msg;
import com.luobo.eduservice.client.VodClient;
import com.luobo.eduservice.entity.EduVideo;
import com.luobo.eduservice.service.EduVideoService;
import com.luobo.servicebase.exceptionhandler.EduException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author luobo
 * @since 2021-06-26
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;
    //添加小节
    @PostMapping("/addVideo")
    public Msg addVideo(@RequestBody EduVideo video){
        videoService.save(video);
        return Msg.success();
    }
    //删除小节
    @DeleteMapping("/removeVideo/{videoId}")
    public Msg removeVideo(@PathVariable String videoId){
        EduVideo eduVideo = videoService.getById(videoId);
        if(!StringUtils.isEmpty(eduVideo.getVideoSourceId())) {
            Msg result = vodClient.removeVideo(eduVideo.getVideoSourceId());
            if(!result.isSuccess()){
                throw new EduException(20001,"熔断器");
            }
        }
        videoService.removeVideoById(videoId);
        return Msg.success();
    }
    //修改小节
    @PostMapping("/updateVideo")
    public Msg updateVideo(@RequestBody EduVideo video){
        videoService.updateById(video);
        return Msg.success();
    }
    @GetMapping("/getVideo/{videoId}")
    public Msg getVideoById(@PathVariable String videoId){
        EduVideo video = videoService.getById(videoId);
        return Msg.success().data("video",video);
    }
}

