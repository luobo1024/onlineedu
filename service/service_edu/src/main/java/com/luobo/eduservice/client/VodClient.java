package com.luobo.eduservice.client;

import com.luobo.commonutils.Msg;
import com.luobo.eduservice.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "service-vod", fallback = VodClientImpl.class)
public interface VodClient {

    //定义调用方法的路径
    //@PathVariable必须指定参数名称
    //删除阿里云视频
    @DeleteMapping("/eduvod/video/removeVideo/{id}")
    public Msg removeVideo(@PathVariable("id") String id);
    //批量删除
    @DeleteMapping("/eduvod/video/delete-batch")
    public Msg deleteBatch(@RequestParam List<String> videoList);
}
