package com.luobo.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.luobo.commonutils.Msg;
import com.luobo.servicebase.exceptionhandler.EduException;
import com.luobo.vod.InitObject;
import com.luobo.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.invoke.ConstantCallSite;
import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;
    @PostMapping("/uploadVideo")
    public Msg uploadVideo(MultipartFile file){
        String videoId = vodService.uploadVideo(file);
        return Msg.success().data("videoId",videoId);
    }
    @DeleteMapping("/removeVideo/{id}")
    public Msg removeVideo(@PathVariable String id){
        try{
            DefaultAcsClient client = InitObject.initVodClient("LTAI5t8hrQj7y1rLQSQLvUzx", "7tFhDqGUEl6G6T1N6458Dvj3lfSokm");
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return Msg.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Msg.error();
    }
    @DeleteMapping("/delete-batch")
    public Msg deleteBatch(@RequestParam List<String> videoList){
        vodService.removeMoreVideo(videoList);
        return Msg.success();
    }

    @GetMapping("/getPlayAuth/{id}")
    public Msg getPlayAuth(@PathVariable String id){
        try{
            DefaultAcsClient client = InitObject.initVodClient("LTAI5t8hrQj7y1rLQSQLvUzx", "7tFhDqGUEl6G6T1N6458Dvj3lfSokm");
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return Msg.success().data("playAuth", playAuth);
        }catch (Exception e){
            throw new EduException(20001,"获取视频失败");
        }
    }
}
