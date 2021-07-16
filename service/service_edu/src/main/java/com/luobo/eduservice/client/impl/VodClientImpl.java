package com.luobo.eduservice.client.impl;

import com.luobo.commonutils.Msg;
import com.luobo.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodClientImpl implements VodClient {
    @Override
    public Msg removeVideo(String id) {
        return Msg.error().message("删除出错");
    }

    @Override
    public Msg deleteBatch(List<String> videoList) {
        return Msg.error().message("删除多个视频出错");
    }
}
