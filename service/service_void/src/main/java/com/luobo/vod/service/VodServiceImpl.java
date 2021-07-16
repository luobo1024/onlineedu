package com.luobo.vod.service;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.luobo.commonutils.Msg;
import com.luobo.vod.InitObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService{
    @Override
    public String uploadVideo(MultipartFile file) {
        try{
            String accessKeyId = "LTAI5t8hrQj7y1rLQSQLvUzx";
            String accessKeySecret = "7tFhDqGUEl6G6T1N6458Dvj3lfSokm";
            String title = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
            String fileName = file.getOriginalFilename();//本地文件路径和名称
            InputStream is = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName,is);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
                videoId = response.getVideoId();
            }
            return videoId;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public void removeMoreVideo(List videoList) {
        try{
            DefaultAcsClient client = InitObject.initVodClient("LTAI5t8hrQj7y1rLQSQLvUzx", "7tFhDqGUEl6G6T1N6458Dvj3lfSokm");
            DeleteVideoRequest request = new DeleteVideoRequest();
            String ids = "";
            for(Object id : videoList){
                ids = ids + id.toString() + ",";
            }
            ids = ids.substring(0, ids.lastIndexOf(","));
            request.setVideoIds(ids);
            client.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
