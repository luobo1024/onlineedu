package com.luobo.vod.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface VodService {
    String uploadVideo(MultipartFile file);

    void removeMoreVideo(List videoList);
}
